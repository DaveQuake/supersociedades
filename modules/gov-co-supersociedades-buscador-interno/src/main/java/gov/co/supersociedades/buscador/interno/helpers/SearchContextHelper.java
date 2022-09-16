package gov.co.supersociedades.buscador.interno.helpers;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;

@Component(immediate=true, service=SearchContextHelper.class)
public class SearchContextHelper {
	
	public SearchContext getSearchContext(ThemeDisplay td, String keyword, long[] parentCategoryIds, int tipo, String start, String end, boolean pagination){
		long[] groupIds = {td.getScopeGroupId()};
		
		SearchContext searchContext =  new SearchContext();
		
		if(Validator.isNotNull(keyword)) {
			keyword = StringPool.QUOTE + keyword + StringPool.QUOTE;
			searchContext.setKeywords(keyword);
		}else {
			searchContext.setAttribute("search.empty.search", Boolean.TRUE);
		}
		
		if(tipo == 1) {
			String[] entryClassNames = new String[] {DLFileEntry.class.getName()};
			searchContext.setEntryClassNames(entryClassNames);
		}else if(tipo == 2) {
			String[] entryClassNames = new String[] {JournalArticle.class.getName()};
			searchContext.setEntryClassNames(entryClassNames);
		}else if(tipo == 3) {
			String[] entryClassNames = new String[] {DLFileEntry.class.getName(), JournalArticle.class.getName()};
			searchContext.setEntryClassNames(entryClassNames);
		}
		
		searchContext.setAssetCategoryIds(parentCategoryIds);
		searchContext.setGroupIds(groupIds);
		searchContext.setCompanyId(td.getCompanyId());
		
		boolean desc = true;
		Sort sortDisplayDate = new Sort(Field.getSortableFieldName(Field.MODIFIED_DATE),Sort.LONG_TYPE, desc);
		Sort[] sorts = new Sort[] {new Sort(Field.MODIFIED_DATE, true)};
		searchContext.setSorts(sorts);
		searchContext.setScopeStrict(true);
		searchContext.setAttribute("head", Boolean.TRUE);
		searchContext.setAttribute("latest", Boolean.TRUE);
		
		if(pagination) {
			if(Validator.isNotNull(start) && Validator.isNotNull(end)) {
				searchContext.setStart(Integer.parseInt(start));
				searchContext.setEnd(Integer.parseInt(end));
			}
		}
		
		return searchContext;
	}
	
	public SearchContext getSearchContextCount(ThemeDisplay td, String keyword, long[] parentCategoryIds, int tipo){
		long[] groupIds = {td.getScopeGroupId()};
		
		SearchContext searchContext =  new SearchContext();
		
		if(Validator.isNotNull(keyword)) {
			keyword = StringPool.QUOTE + keyword + StringPool.QUOTE;
			searchContext.setKeywords(keyword);
		}else {
			searchContext.setAttribute("search.empty.search", Boolean.TRUE);
		}
		
		if(tipo == 1) {
			String[] entryClassNames = new String[] {DLFileEntry.class.getName()};
			searchContext.setEntryClassNames(entryClassNames);
		}else if(tipo == 2) {
			String[] entryClassNames = new String[] {JournalArticle.class.getName()};
			searchContext.setEntryClassNames(entryClassNames);
		}else if(tipo == 3) {
			String[] entryClassNames = new String[] {DLFileEntry.class.getName(), JournalArticle.class.getName()};
			searchContext.setEntryClassNames(entryClassNames);
		}
		
		searchContext.setAssetCategoryIds(parentCategoryIds);
		searchContext.setGroupIds(groupIds);
		searchContext.setCompanyId(td.getCompanyId());
		
		boolean desc = true;
		Sort[] sorts = new Sort[] {new Sort(Field.MODIFIED_DATE, true)};
		searchContext.setSorts(sorts);
		searchContext.setScopeStrict(true);
		searchContext.setAttribute("head", Boolean.TRUE);
		searchContext.setAttribute("latest", Boolean.TRUE);
		
		return searchContext;
	}

}
