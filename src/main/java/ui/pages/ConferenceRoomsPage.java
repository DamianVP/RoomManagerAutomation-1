package ui.pages;

import entities.ConferenceRoom;
import entities.Resource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.BaseMainPageObject;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/7/15
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConferenceRoomsPage extends BaseMainPageObject {

    @FindBy(xpath = "//div[@class='row']")
    @CacheLookup
    private WebElement resourcesPanel;

    @FindBy(id = "roomsGrid")
    @CacheLookup
    private WebElement roomsTable;

    @FindBy(xpath = "//input[@type='text' and @ng-model='filterOptions.filterText']")
    @CacheLookup
    private WebElement inputFilterByRoom;

    @FindBy(xpath = "//div[contains(@class,'ng-scope ngRow')]")
    private WebElement rowConferenceRoom;

    /**
     * Constructor
     */
    public ConferenceRoomsPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
    }

    private ConferenceRoomsPage setInputFilterByRoom(String value){
        inputFilterByRoom.clear();
        inputFilterByRoom.sendKeys(value);
        return this;
    }

    private WebElement searchRoom(String roomDisplayName){
        return roomsTable.findElement(By.xpath("//div[span[contains(text(),'" +
                                                roomDisplayName + "')]]"));
    }

    private WebElement searchResource(String resourceDisplayName){
        return resourcesPanel.findElement(By.xpath("//div[@ng-repeat='resource in resources']/span[span[contains(text(),'" + resourceDisplayName + "')]]"));
    }

    public RoomInfoPage clickOnSpecificRoom(ConferenceRoom room){
        WebElement buttonRoom=searchRoom(room.getDisplayName());
        buttonRoom.click();
        return new RoomInfoPage();
    }

    /**
     *
     * @param room
     * @return RoomInfoPage
     */
    public RoomInfoPage doubleClickOnSpecificRoom(ConferenceRoom room){
        Actions action = new Actions(driver);
        WebElement buttonRoom = searchRoom(room.getDisplayName());
        action.moveToElement(buttonRoom).perform();
        action.doubleClick(buttonRoom).perform();
        return new RoomInfoPage();
    }

    /**
     * @param resource
     * @return ConferenceRoomsPage
     */

    public ConferenceRoomsPage clickOnSpecificResource(Resource resource){
        WebElement buttonResource=searchResource(resource.getDisplayName());
        buttonResource.click();
        return this;
    }

    /**
     * This method is verified that the message is displayed
     * @param message
     * @return
     */
    public boolean isMessagePresent(String message){
        try {
            return driver.findElement(By.xpath("//div[@class='ng-binding ng-scope' and contains(text(),'" + message + "')]")).isDisplayed();
        }   catch (Exception e){
            return false;
        }
    }

    /**
     * This method is verified that a resource is associated with a specific room
     * @param quantity
     * @param room
     * @return
     */
    public boolean isResourceAssociate(String quantity, ConferenceRoom room){
        try {
            return roomsTable.findElement(By.xpath("//div[div/div[span[contains(text(),'" + room.getDisplayName() + "')]]]/following-sibling::div//span[contains(text(),' x "+quantity+" ')]")).isDisplayed();
        }   catch (Exception e){
            return false;
        }
    }

    /**
     * This method disables or enables a specific room
     * @param room
     * @return
     */
    public ConferenceRoomsPage clickOnDisabledConferenceRoom(ConferenceRoom room){
        WebElement disabledButton = roomsTable.findElement(By.xpath("//div[div/div[span[contains(text(),'" + room.getDisplayName() + "')]]]/preceding-sibling::div/preceding-sibling::div//span"));
        disabledButton.click();
        return this;
    }

    /**
     * This method verified that a room is disabled
     * @param room
     * @return
     */
    public boolean isConferenceRoomsDisabled(ConferenceRoom room){
        try {
            WebElement conferenceRoom = roomsTable.findElement(By.xpath("//div[div/div[span[contains(text(),'" + room.getDisplayName() + "')]]]/preceding-sibling::div/preceding-sibling::div//div[@ng-if='(row.entity.enabled == false)']"));
            return conferenceRoom.isDisplayed();
        }   catch(Exception e){
            return false;
        }
    }

    /**
     * THis method verified that a calendar icon is present on specific room
     * @param room
     * @return
     */
    public boolean isCalendarPresent(ConferenceRoom room){
        try {
            WebElement calendar = roomsTable.findElement(By.xpath("//div[div/div[span[contains(text(),'" + room.getDisplayName() + "')]]]/preceding-sibling::div"));
            return calendar.isDisplayed();
        }   catch(Exception e){
            return false;
        }
    }

    /**
     * This method verified that any resource columns is displayed on the window
     */
    public void ensureIsNotDisplayedResourceColumns(){
        List<WebElement> resourcesList = resourcesPanel.findElements(By.xpath("//div[@ng-repeat='resource in resources']"));
        for (WebElement option : resourcesList) {
            try {
                if(option.findElement(By.xpath("//div[@ng-repeat='resource in resources']/span[@class='btn btn-default btn-block ng-pristine ng-untouched ng-valid active'][span[contains(text(),'"+option.getText()+"')]]")).isDisplayed()){
                    WebElement op = option.findElement(By.xpath("//div[@ng-repeat='resource in resources']/span[span[contains(text(),'"+option.getText()+"')]]"));
                    op.click();
                }
            }   catch (Exception ignored){
            }
        }
    }
}
