package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class SampleController implements Initializable {

	@FXML
	private Button btn0;

	@FXML
	private Button btn1;

	@FXML
	private Button btn2;

	@FXML
	private Button btn3;

	@FXML
	private Button btn4;

	@FXML
	private Button btn5;

	@FXML
	private Button btn6;

	@FXML
	private Button btn7;

	@FXML
	private Button btn8;

	@FXML
	private Button restartBtn;

	@FXML
	private Text ristinollaTeksti;

	private ArrayList <Button> btnList;
	private int pelaajaVuoro = 1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnList = new ArrayList<>(Arrays.asList(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8));
		restartBtn.setFocusTraversable(false);
		btnList.forEach(btn -> {
			setNappiTeksti(btn);
			btn.setFocusTraversable(false);
		});

	}

	private void setNappiTeksti(Button btn) {
		btn.setOnMouseClicked(mouseEvent ->{
			btn.setText(pelaajaMerkki());
			btn.setDisable(true);
			onkoLoppu();
		});
	}


	private String pelaajaMerkki() {
		if(pelaajaVuoro == 1) {
			pelaajaVuoro -= 1;
			return "X";
		}else
			pelaajaVuoro += 1;
		return "O";
	}

	private void onkoLoppu() {
		String mjono = "";
		for(int i = 0; i < 8; i++) {
			switch(i) {
			case 0:
				mjono = btn0.getText() + btn1.getText() + btn2.getText();
				break;
			case 1:
				mjono = btn3.getText() + btn4.getText() + btn5.getText();
				break;
			case 2:
				mjono = btn6.getText() + btn7.getText() + btn8.getText();
				break;
			case 3:
				mjono = btn0.getText() + btn3.getText() + btn6.getText();
				break;
			case 4:
				mjono = btn1.getText() + btn4.getText() + btn7.getText();
				break;
			case 5:
				mjono = btn2.getText() + btn5.getText() + btn8.getText();
				break;
			case 6:
				mjono = btn0.getText() + btn4.getText() + btn8.getText();
				break;
			case 7:
				mjono = btn6.getText() + btn4.getText() + btn2.getText();
				break;
			default:
				mjono = "but how???";
				return;
			}
			if(mjono.equals("XXX")) {
				ristinollaTeksti.setText("X voitti");
				lopetaPeli();

			}else if(mjono.equals("OOO")) {
				ristinollaTeksti.setText("O voitti");
				lopetaPeli();
			}
		}
	}

	private void lopetaPeli() {
		btnList.forEach(btn->{
			btn.setDisable(true);
		});
	}


	@FXML
	void restart(ActionEvent event) {
		pelaajaVuoro = 1;
		ristinollaTeksti.setText("Ristinolla");
		for(Button btn : btnList) {
			btn.setDisable(false);
			btn.setText("");
		}
	}
}