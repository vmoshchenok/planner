package toDoTests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import po.ToDoPage;

import static com.codeborne.selenide.Selenide.open;

@Epic("ToDo")
@Feature("ToDo list")
@Story("ToDo list negative scenarios")
public class ToDoNegativeScenarios {
    private ToDoPage toDoPage;

    @BeforeMethod
    public void init() {
        toDoPage = new ToDoPage();
        open("https://todomvc.com/examples/angular2/");
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        Selenide.closeWebDriver();
    }

    @Test(description = "TC#01 Verifying that it is impossible to save an empty item")
    public void verifySavingEmptyItem() {
        toDoPage.createToDoItem("");
        toDoPage.checkIfNoItemsPresent();
    }

    @Test(description = "TC#02 Verifying that it is impossible to edit an empty item")
    public void verifyEditingEmptyItems() {
        toDoPage.createToDoItem("Item for editing");
        toDoPage.editItem("Item for editing", "");
        toDoPage.checkIfNoItemsPresent(); //Item with empty text should be removed, but it's a bug now - empty item is present
    }

    @Test(description = "TC#04 Verifying that JavaScript text is displayed as plain text")
    public void verifyAbilityToAddJavaScript() {
        String item = "<script>alert('Hello')</script>";

        toDoPage.createToDoItem(item);
        toDoPage.competeToDoItem(item);
        toDoPage.checkIfToDoItemCompleted(item);
        toDoPage.clearCompletedItems();
        toDoPage.checkIfCompletedItemsRemoved();
    }

    @Test(description = "TC#04 Verifying that long text could be")
    public void verifyAbilityToAddLongText() {
              String item = "Amsterdam was founded at the Amstel, that was dammed to control flooding; the city's name derives " +
                "from the Amstel dam.[15] Originating as a small fishing village in the late 12th century, " +
                "Amsterdam became one of the most important ports in the world during the Dutch Golden Age of the 17th century, " +
                "and became the leading centre for the finance and trade sectors.[16] In the 19th and 20th centuries, " +
                "the city expanded and many new neighborhoods and suburbs were planned and built. " +
                "The 17th-century canals of Amsterdam and the 19–20th century Defence Line of Amsterdam are on the UNESCO World Heritage List. " +
                "Sloten, annexed in 1921 by the municipality of Amsterdam, is the oldest part of the city, dating to the 9th century.";

        toDoPage.createToDoItem(item);
        toDoPage.competeToDoItem(item);
        toDoPage.checkIfToDoItemCompleted(item);
        toDoPage.clearCompletedItems();
        toDoPage.checkIfCompletedItemsRemoved();
    }
}
