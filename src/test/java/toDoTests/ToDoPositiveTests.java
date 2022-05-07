package toDoTests;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import po.ToDoPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@Epic("ToDo")
@Feature("ToDo list")
@Story("ToDo list positive scenarios")
public class ToDoPositiveTests {
    private ToDoPage toDoPage;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        toDoPage = new ToDoPage();
        open("https://todomvc.com/examples/angular2/");
    }

    @AfterMethod(alwaysRun = true)
    public void closeWebDriver() {
        Selenide.closeWebDriver();
    }


    @Test(description = "TC#01 Verifying the ToDo page layout")
    @Description("first tesr")
    public void verifyPageLayOut() {
        toDoPage.checkPageLayout();
    }

    @Test(description = "TC#02 Verifying that a new item is added to the ToDo list")
    public void verifyCreatingNewItem() {
        String item = generateItem();

        toDoPage.createToDoItem(item);
        toDoPage.checkIfToDoItemAdded(item);
    }
    @Test(description = "TC#03 Verifying that it's possible to complete items and clear completed items")
    public void verifyCompletingItemScenario() {
        String item = generateItem();

        toDoPage.createToDoItem(item);
        toDoPage.competeToDoItem(item);
        toDoPage.checkIfToDoItemCompleted(item);
        toDoPage.clearCompletedItems();
        toDoPage.checkIfCompletedItemsRemoved();
    }

    @Test(description = "TC#04 Verifying that it's possible to delete item")
    public void verifyDeletingItemScenario() {
        String item = generateItem();

        toDoPage.createToDoItem(item);
        toDoPage.deleteToDoItem(item);
        toDoPage.checkIfToDoItemDeleted(item);
    }

    @Test(description = "TC#05 Verifying the ability to create a few items")
    public void verifyCreatingFewItems() {
        toDoPage.createFewItems(generateItemsCollection(5));
        toDoPage.checkCountOfLeftItems("5");
    }

    private static String generateItem() {
        Faker faker = new Faker();
        return faker.harryPotter().character();
    }

    private List<String> generateItemsCollection(int count) {
        List<String> items = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < count; i++) {
            items.add(faker.harryPotter().character());
        }
        return items;
    }

}
