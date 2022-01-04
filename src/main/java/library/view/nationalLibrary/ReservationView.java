package library.view.nationalLibrary;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.HtmlObject;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Route("reservation")
public class ReservationView extends VerticalLayout {

    ReservationService reservationService;

    ReservationView(ApplicationContext applicationContext) {

        reservationService = applicationContext.getBean(ReservationService.class);
        VerticalLayout choiceLayout = new VerticalLayout();

        DatePicker datePicker = new DatePicker("Schedule Your visit!", LocalDate.now().plusDays(1));



        TextArea confirmationTextArea = new TextArea();
        Button orderButton = new Button("Reserve!");

        IntegerField clId = new IntegerField("Enter Your Id");
        clId.setMinWidth(200, Unit.PIXELS);
        datePicker.setMinWidth(200, Unit.PIXELS);

        choiceLayout.add(
                clId,
                datePicker,
                orderButton
        );

        confirmationTextArea.setMinWidth(500, Unit.PIXELS);
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
                    "\nDate: " + datePicker.getValue());
            orderButton.setVisible(false);
            confirmationTextArea.setEnabled(false);
        });


        add(htmlComponent, secRowLayout);


        NatLibBookDto natLibBookDto =
                applicationContext.getBean("selectedBook", NatLibBookDto.class);
        confirmationTextArea.setValue(natLibBookDto.toString());

        OrderedBook orderedBook = new OrderedBook(
                natLibBookDto.getAuthor(), natLibBookDto.getTitle(), natLibBookDto.getId());

        orderButton.addClickListener(click -> {
            String message = confirmationTextArea.getValue();

            confirmationTextArea.setValue("\nYour reservation has been confirmed!\n\n" + message);
            orderButton.setVisible(false);
            confirmationTextArea.setEnabled(false);
            

            ReservationDto reservationDto =
                    ReservationDto.builder()
                            .id(0)
                            .clientId(clId.getValue())
                            .orderedBook(orderedBook)
                            .date(datePicker.getValue())
                            .reservationStatus(ReservationStatus.RESERVED)
                            .build();

            reservationService.save(reservationDto);
        });


    }


}


