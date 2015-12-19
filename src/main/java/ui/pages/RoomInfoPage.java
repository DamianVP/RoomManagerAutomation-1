package ui.pages;

import entities.Location;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.BasePageConferenceRoom;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/7/15
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoomInfoPage extends BasePageConferenceRoom{

    @FindBy(className = "btn btn-default")
    @CacheLookup
    private WebElement buttonRoomEnabled;

    @FindBy(className = "btn btn-default ng-hide")
    @CacheLookup
    private WebElement buttonRoomDisabled;

    @FindBy(xpath = "//input[@type='text' and @ng-model='selectedRoom.displayName']")
    @CacheLookup
    private WebElement  getPutName;

    @FindBy(xpath = "//input[@type='text' and @ng-model='selectedRoom.customDisplayName']")
    @CacheLookup
    private WebElement  inputDisplayName;

    @FindBy(xpath = "//input[@type='text' and @ng-model='selectedRoom.code']")
    @CacheLookup
    private WebElement inputCode;

    @FindBy(xpath = "//input[@type='text' and @ng-model='selectedRoom.capacity']")
    @CacheLookup
    private WebElement inputCapacity;

    @FindBy(xpath = "//button[@ng-click='toggleTree()']")
    @CacheLookup
    private WebElement buttonLocation;

    @FindBy(xpath = "//div[@class='treeview-toggle']/span[@ng-click='collapse($event)']")
    @CacheLookup
    private WebElement buttonCollapseLocation;

    @FindBy(xpath = "//div[@class='treeview-branches ng-scope']")
    @CacheLookup
    private WebElement locationBranches;

    public RoomInfoPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
    }

    private RoomInfoPage clickRoomEnabledButton(){
        buttonRoomEnabled.click();
        return this;
    }

    private RoomInfoPage clickRoomDisabledButton(){
        buttonRoomDisabled.click();
        return this;
    }

    private RoomInfoPage clickLocationButton(){
        buttonLocation.click();
        return this;
    }

    private RoomInfoPage clickCollapseLocationButton(){
        buttonCollapseLocation.click();
        return this;
    }

    private RoomInfoPage setInputDisplayName(String value){
        inputDisplayName.clear();
        inputDisplayName.sendKeys(value);
        return this;
    }

    private RoomInfoPage setInputCode(String value){
        inputCode.clear();
        inputCode.sendKeys(value);
        return this;
    }

    private RoomInfoPage setInputCapacity(String value){
        inputCapacity.clear();
        inputCapacity.sendKeys(value);
        return this;
    }

    private RoomInfoPage setInputWebElement(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
        return this;
    }

    /**
     * This method go to Association Page
     * @return
     */
    public ResourceAssociationsPage gotoAssociationPage(){
        return clickResourceAssociationsTab();
    }

    /**
     * This method go to OutOfOrderPage
     * @return
     */
    public OutOfOrderPlanningPage gotoOutOfOrderPlanningPage(){
        return clickOutOfOrderPlanningTab();
    }

    /**
     * This Method associated a room to location
     * @param location
     * @return ConferenceRoomsPage
     */
    public ConferenceRoomsPage setAssociateLocation(Location location){
        clickLocationButton();
        clickCollapseLocationButton();
        WebElement specificLocation = locationBranches.findElement(By.xpath("//transclude/div[contains(text(),'" + location.getDisplayName() + "')]"));
        specificLocation.click();
        return clickSaveButton();
    }
}
