package consumoAPIS;

public class Image {
	private String path;
    private String extension;

    
    
    public Image(String path, String extension) {
		super();
		this.path = path;
		this.extension = extension;
	}
    public Image() {
    	
    }

	public String getPath() {
        return path;
    }

    public String getExtension() {
        return extension;
    }

    public String getFullImagePath(Image image) {
    	return (image != null) ? image.getPath() + "." + image.getExtension() : "N/A";
    }

	public void setPath(String path) {
		this.path = path;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
    
    
}
