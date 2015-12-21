package entities;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/9/15
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Resource {
    private String id;
    private String name;
    private String displayName;
    private String description;
    private String icon;
    private int quantity;

    public void setID(String id) {this.id = id;}

    public void setName(String newName){name=newName;}

    public void setDisplayName(String newDisplayName){displayName=newDisplayName;}

    public void setDescription(String newDescription){description=newDescription;}

    public void setIcon(String newIcon){icon=newIcon;}

    public void setQuantity(int quantity1){quantity=quantity1;}

    public String getID() {return id;}

    public String getName(){ return name;}

    public String getDisplayName(){ return displayName;}

    public String getDescription(){ return description;}

    public String getIcon(){ return icon;}

    public int getQuantity() {return quantity;}




}
