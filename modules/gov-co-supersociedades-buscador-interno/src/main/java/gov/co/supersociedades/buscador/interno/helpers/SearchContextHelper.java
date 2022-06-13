package gov.co.supersociedades.buscador.interno.helpers;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;

@Component(immediate=true, service=SearchContextHelper.class)
public class SearchContextHelper {
	
	public SearchContext getSearchContext(ThemeDisplay td, String keyword, long[] parentCategoryIds, boolean isDlFile, boolean isJournalArticle){
		long[] groupIds = {td.getScopeGroupId()};
		
		SearchContext searchContext =  new SearchContext();
		
		if(Validator.isNotNull(keyword)) {
			keyword = StringPool.QUOTE + keyword + StringPool.QUOTE;
			searchContext.setKeywords(keyword);
		}else {
			searchContext.setAttribute("search.empty.search", Boolean.TRUE);
		}
		
		if(isDlFile) {
			String[] entryClassNames = new String[] {DLFileEntry.class.getName()};
			searchContext.setEntryClassNames(entryClassNames);
		}
		if(isJournalArticle) {
			String[] entryClassNames = new String[] {JournalArticle.class.getName()};
			searchContext.setEntryClassNames(entryClassNames);
		}
		
		searchContext.setAssetCategoryIds(parentCategoryIds);
		searchContext.setGroupIds(groupIds);
		searchContext.setCompanyId(td.getCompanyId());
		
		boolean desc = true;
		Sort sortDisplayDate = new Sort(Field.getSortableFieldName(Field.PUBLISH_DATE),Sort.LONG_TYPE, desc);
		String localizedField = "localized_"+Field.getLocalizedName(td.getLocale(), Field.TITLE);
		Sort sortTitle = new Sort(Field.getSortableFieldName(localizedField),!desc);
		searchContext.setSorts(sortDisplayDate, sortTitle);
		
		searchContext.setScopeStrict(true);
		searchContext.setAttribute("head", Boolean.TRUE);
		searchContext.setAttribute("latest", Boolean.TRUE);
		
		return searchContext;
	}

}
