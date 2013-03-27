package formo.domain;

public class Type{
	
	private int id;
	private String name;
	private int authorId;
	private String raw;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getAuthorId(){
		return this.authorId;
	}
	
	public void setAuthorId(int authorId){
		this.authorId = authorId;
	}
	
	public String getRaw(){
		return this.raw;
	}
	
	public void setRaw(String raw){
		this.raw = raw;
	}
	
	
	
	
	public String toString(){
		return "{ type : { \"id\" : \"" + this.id + "\", \"name : \" : \"" + this.name + "\"}";
	}
	
}