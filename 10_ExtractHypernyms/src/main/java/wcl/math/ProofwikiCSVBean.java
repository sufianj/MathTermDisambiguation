package wcl.math;

import com.opencsv.bean.CsvBindByName;

public class ProofwikiCSVBean {
	public String getDd_page_title() {
		return dd_page_title;
	}

	public void setDd_page_title(String dd_page_title) {
		this.dd_page_title = dd_page_title;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getDef_page_title() {
		return def_page_title;
	}

	public void setDef_page_title(String def_page_title) {
		this.def_page_title = def_page_title;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getPlain_text_definition() {
		return plain_text_definition;
	}

	public void setPlain_text_definition(String plain_text_definition) {
		this.plain_text_definition = plain_text_definition;
	}
	
	public String getHypernym() {
		return hypernym;
	}

	public void setHypernym(String hypernym) {
		this.hypernym = hypernym;
	}

	public Boolean getIs_definition() {
		return is_definition;
	}

	public void setIs_definition(Boolean is_definition) {
		this.is_definition = is_definition;
	}

	@Override
	public String toString() {
		return "ProofwikiCSVBean [dd_page_title=" + dd_page_title + ", term=" + term + ", definition=" + definition
				+ ", def_page_title=" + def_page_title + ", categories=" + categories + ", plain_text_definition="
				+ plain_text_definition + ", hypernym=" + hypernym + ", is_definition=" + is_definition + "]";
	}



	@CsvBindByName(column = "dd_page_title")
    private String dd_page_title;

	@CsvBindByName(column = "term")
    private String term;

	@CsvBindByName(column = "definition")
    private String definition;

	@CsvBindByName(column = "def_page_title")
    private String def_page_title;
    
	@CsvBindByName(column = "categories")
    private String categories;

	@CsvBindByName(column = "plain_text_definition")
    private String plain_text_definition;
	
	@CsvBindByName(column = "hypernym")
    private String hypernym;

	@CsvBindByName(column = "is_definition")
    private Boolean is_definition;
}
