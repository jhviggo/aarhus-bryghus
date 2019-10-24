package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.ProductGroup;

public class RegisterProductPane extends GridPane {
	
	private ComboBox<ProductGroup> cmbProductGroup;
	private Button btnCancel;
	private Button btnSave;
	private Label lblProductGroup;
	private Label lblType;

	public RegisterProductPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
	}

}

