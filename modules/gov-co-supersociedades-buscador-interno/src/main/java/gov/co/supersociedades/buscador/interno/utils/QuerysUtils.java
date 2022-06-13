package gov.co.supersociedades.buscador.interno.utils;

import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate=true, service=QuerysUtils.class)
public class QuerysUtils {

	public String getArticlePageURL(JournalArticle ja, ThemeDisplay themeDisplay) {
	    DynamicQuery query = DynamicQueryFactoryUtil.forClass(PortletPreferences.class)
	            .add(PropertyFactoryUtil.forName("portletId").like("com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_%"))
	            .add(PropertyFactoryUtil.forName("preferences").like("%<name>groupId</name><value>"+ja.getGroupId()+"</value>%"))
	            .add(PropertyFactoryUtil.forName("preferences").like("%<name>articleId</name><value>"+ja.getArticleId()+"</value>%"));
	         
	    List<PortletPreferences> portlets = PortletPreferencesLocalServiceUtil.dynamicQuery(query);
	         
	    if (portlets.size()>0) {
	        try {
	            Layout layout = _layoutLocalService.getLayout(portlets.get(0).getPlid());
	            return PortalUtil.getLayoutFriendlyURL(layout, themeDisplay);

	        } catch (Exception e) { 
	        	_log.error(e);
	        }
	    }
	         
	    return StringPool.BLANK;
    }

	private static final Log _log = LogFactoryUtil.getLog(QuerysUtils.class);
	
	@Reference
	private LayoutLocalService _layoutLocalService;
}
