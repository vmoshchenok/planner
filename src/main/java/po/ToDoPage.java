package po;
import java.util.List;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ToDoPage {

    @Step("Verify the ToDo list page layout")
    public void checkPageLayout() {
        $x("//h1").shouldHave(text("todos"));
        $x("//*[@class = 'info']").shouldHave(text("Double-click to edit a todo\n" +
                "Created by Sam Saccone and Colin Eberhardt using Angular2\n" +
                "Part of TodoMVC"));
        $x("//a[@href = 'http://github.com/samccone']").shouldHave(text("Sam Saccone"));
        $x("//a[@href = 'http://github.com/colineberhardt']").shouldHave(text("Colin Eberhardt"));
        $x("//a[@href = 'http://angular.io']").shouldHave(text("Angular2"));
        $x("//a[@href = 'http://todomvc.com']").shouldHave(text("TodoMVC"));
        $x("//*[contains(@class, 'new-todo')]").shouldHave(attribute("placeholder", "What needs to be done?"));
    }

    @Step("Create a new ToDo item")
    public void createToDoItem(String value) {
        $x("//*[contains(@class, 'new-todo')]").val(value);
        $x("//*[contains(@class, 'new-todo')]").pressEnter();
    }

    @Step("Verify that the item was added to the list")
    public void checkIfToDoItemAdded(String value) {
        $x("//label[text() = '" + value + "']").should(exist);
    }

    @Step("Edit the item")
    public void editItem(String oldValue, String newValue) {
        $x("//label[text() = '" + oldValue + "']").doubleClick();
        $x("//*[@class = 'edit']").setValue(newValue);
    }

    @Step("Complete a Todo item")
    public void competeToDoItem(String value) {
        $x("//label[text() = \"" + value + "\"]//preceding-sibling::input").click();
    }

    @Step("Delete a Todo item")
    public void deleteToDoItem(String value) {
        $x("//label[text() = \"" + value + "\"]").click();
        $x("//label[text() = \"" + value + "\"]//following-sibling::button").click();
    }

    @Step("Verify that the Todo item was completed")
    public void checkIfToDoItemCompleted(String value) {
        $x("//*[@class = 'completed']//label[text() = \"" + value + "\"]").should(visible);
    }

    @Step("Verify that the Todo item was deleted")
    public void checkIfToDoItemDeleted(String value) {
        $$x("//label[text() = \"" + value + "\"]").shouldHave(CollectionCondition.size(0));
    }

    @Step("Check the count of items")
    public void checkCountOfLeftItems(String count) {
        $x("//strong").shouldHave(text(count));
        $x("//*[@class = 'todo-count']").shouldHave(text(count + " items left"));
    }

    @Step("Check that the ToDo  list is empty ")
    public void checkIfNoItemsPresent(){
        $x("//*[@class = 'todo-count']").should(not(exist));
    }

    @Step("Clear completed items")
    public void clearCompletedItems() {
        $x("//*[@class = 'clear-completed']").click();
    }

    @Step("Verify that completed items was removed")
    public void checkIfCompletedItemsRemoved() {
        $$x("//*[@class = 'clear-complete']").shouldHave(CollectionCondition.size(0));
    }

    @Step("Create a few items")
    public void createFewItems(List<String> items) {
        items.forEach(item -> {
            createToDoItem(item);
            checkIfToDoItemAdded(item);
        });
    }
}
