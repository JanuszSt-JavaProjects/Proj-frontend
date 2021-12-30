package library.frontend.view;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class StartView extends VerticalLayout {


    Button libraryBtn = new Button("Enter Library");
    Button readersBtn = new Button("Enter Readers Room");

    StartView() {

        setAlignItems(Alignment.CENTER);

        libraryBtn.setMinHeight(100, Unit.PIXELS);
        libraryBtn.setMinWidth(300, Unit.PIXELS);
        readersBtn.setMinHeight(100, Unit.PIXELS);
        readersBtn.setMinWidth(300, Unit.PIXELS);




        libraryBtn.addClickListener(e ->
                libraryBtn.getUI().ifPresent(ui ->
                        ui.navigate("library")));

                readersBtn.addClickListener(e ->
                readersBtn.getUI().ifPresent(ui ->
                        ui.navigate("readers"))


        );



        add(libraryBtn, readersBtn);
    }

}
