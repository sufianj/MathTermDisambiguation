package wcl.math;
import com.opencsv.bean.CsvBindByName;
public class ArXivCSVBean {
//term,def,file_name,extraction_rule,plain_text_def,plain_text_term

	/*@CsvBindByName(column = "term",required = false)
    private String term;

	@CsvBindByName(column = "def",required = false)
    private String def;*/

	@CsvBindByName(column = "file_name",required = false)
    private String file_name;
    
	@CsvBindByName(column = "extraction_rule",required = false)
    private String extraction_rule;

	@CsvBindByName(column = "plain_text_def",required = false)
    private String plain_text_def;
	
	@CsvBindByName(column = "plain_text_term",required = false)
    private String plain_text_term;
	
	@CsvBindByName(column = "hypernym",required = false)
    private String hypernym;

	@CsvBindByName(column = "is_definition", required = false)
    private Boolean is_definition;

/*	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}*/

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getExtraction_rule() {
		return extraction_rule;
	}

	public void setExtraction_rule(String extraction_rule) {
		this.extraction_rule = extraction_rule;
	}

	public String getPlain_text_def() {
		return plain_text_def;
	}

	public void setPlain_text_def(String plain_text_def) {
		this.plain_text_def = plain_text_def;
	}

	public String getPlain_text_term() {
		return plain_text_term;
	}

	public void setPlain_text_term(String plain_text_term) {
		this.plain_text_term = plain_text_term;
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

	/*@Override
	public String toString() {
		return "ArXivCSVBean [term=" + term + ", def=" + def + ", file_name=" + file_name + ", extraction_rule="
				+ extraction_rule + ", plain_text_def=" + plain_text_def + ", plain_text_term=" + plain_text_term
				+ ", hypernym=" + hypernym + ", is_definition=" + is_definition + "]";
	}*/
	
	
}
