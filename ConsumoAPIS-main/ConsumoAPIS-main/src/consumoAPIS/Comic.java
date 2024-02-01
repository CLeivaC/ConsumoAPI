package consumoAPIS;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Comic {

	private int id;
	private int digital_id;
	private String title;
	private int issueNumber;
	private Image thumbnail;

	// Si el campo es vacio, no se incluira en el fichero JSON.
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String variantDescription;

	
	
	private String format;
	private String path_image;
	
	private int nStories;
	private String modified;
	private String language;

	public Comic(int id, int digital_id, String title, int issueNumber, String variantDescription,String path_image, String format,int nStories,String modified,String language) {
		 this.id = id;
	        this.digital_id = digital_id;
	        this.title = title;
	        this.issueNumber = issueNumber;
	        this.variantDescription = variantDescription;
	        this.path_image = path_image;
	        this.format = format;

	        // Crear una instancia de Image con la URL proporcionada
	        
	        this.thumbnail = new Image();
	        this.thumbnail.setPath(path_image);
	        // Puedes ajustar esto según cómo manejas las extensiones en tu aplicación
	        this.thumbnail.setExtension("jpg");
	        
	      this.nStories = nStories;
	        this.modified = modified;
	        this.language = language;
	}

	public Comic() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDigitalId() {
		return digital_id;
	}

	public void setDigitalId(int digital_id) {
		this.digital_id = digital_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(int issueNumber) {
		this.issueNumber = issueNumber;
	}

	public String getVariantDescription() {
		return variantDescription;
	}

	public void setVariantDescription(String variantDescription) {
		this.variantDescription = variantDescription;
	}

	public Image getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Image thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	public String getPath_image() {
	    if (path_image != null && !path_image.isEmpty()) {
	        // Si el campo path_image está presente, simplemente devuélvelo
	        return path_image;
	    } else if (thumbnail != null) {
	        // Si thumbnail está presente, utiliza el método de la clase Image para obtener la URL completa
	        return thumbnail.getFullImagePath(thumbnail);
	    } else {
	        // Si ambos son nulos, devuelve "N/A"
	        return "N/A";
	    }
	}
	 
	 public void setPath_image(String path_image) {
		 this.path_image = path_image;
	 }

	
	 
	
	
	 
	public int getnStories() {
		return nStories;
	}

	public void setnStories(int nStories) {
		this.nStories = nStories;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	
	 
	 
	 
}
