package library.frontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.HtmlObject;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import library.backend.readingRoom.NationalLibrary.domain.NatLibBookDto;
import library.backend.readingRoom.reservation.domain.Hour;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@Route("reservation")
public class ReservationView extends VerticalLayout {

    ReservationView(ApplicationContext applicationContext) {

        VerticalLayout choiceLayout = new VerticalLayout();

        DatePicker datePicker = new DatePicker("Schedule Your visit!", LocalDate.now().plusDays(1));

        CheckboxGroup<String> hoursGroup = new CheckboxGroup<>();
        hoursGroup.setLabel("Choose hours");
        hoursGroup.setItems(Hour.HOUR_10_11.getName(),
                Hour.HOUR_11_12.getName(),
                Hour.HOUR_12_13.getName(),
                Hour.HOUR_13_14.getName());
        hoursGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        TextArea confirmationTextArea = new TextArea();
        Button orderButton = new Button("Reserve!");

        choiceLayout.add(
                datePicker,
                hoursGroup,
                orderButton
        );

        HorizontalLayout secRowLayout = new HorizontalLayout();
        secRowLayout.add(choiceLayout, confirmationTextArea);

        HtmlObject htmlComponent = new HtmlObject("forecast.html");
        htmlComponent.setSizeFull();
        htmlComponent.setHeight("400px");


        confirmationTextArea.setValue(
                applicationContext.getBean("selectedBook", NatLibBookDto.class).toString());


        orderButton.addClickListener(click -> {
            String message = confirmationTextArea.getValue();
            confirmationTextArea.setValue(message + "\nYour reservation has been confirmed!" +
                    "\nDate: " + datePicker.getValue() +
                    " time: " + hoursGroup.getSelectedItems());
            orderButton.setVisible(false);
        });


        add(htmlComponent, secRowLayout);
    }
}
