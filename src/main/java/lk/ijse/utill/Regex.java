package lk.ijse.utill;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Paint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text){
        String filed = "";

        switch (textField){
            case ID:
                filed = "^([A-Z][0-9]{3})$";
                break;
            case NAME:
                filed = "^[A-z|\\\\s]{3,}$";
                break;
            case EMAIL:
                filed = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;
            case ADDRESS:
                filed ="^[a-zA-Z0-9\\\\s.,'-]+$";
                break;
            case CONTACT:
                filed ="^(?:7|0|(?:\\+94))[0-9]{9,10}$";
                break;
            case QTY:
                filed ="^0*(\\d{1,9})$";
                break;
            case NIC:
                filed ="^([0-9]{9}[x|X|v|V]|[0-9]{12})$";
                break;
            case DATE:
                filed ="^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
                 break;
            case USERNAME:
                filed ="^[a-zA-Z0-9_.]+$";
                break;
            case PASSWORD:
                filed ="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$";
                break;
            case AMOUNT:
                filed ="^\\d+(\\.\\d{1,2}.)?$";
                break;
        }

        Pattern pattern = Pattern.compile(filed);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }


    public static boolean setTextColor(TextField location, javafx.scene.control.TextField textField){
        if (Regex.isTextFieldValid(location, textField.getText())){
            textField.setStyle(" -fx-text-box-border: #34d734;\n" +
                    "    -fx-focus-color: #12dc12;\n" +
                    "    -fx-faint-focus-color: rgba(246,68,68,0);;");

            return true;
        }else {
            textField.setStyle(" -fx-text-box-border: #f10000;\n" +
                    "    -fx-focus-color: #ff0000;\n" +
                    "    -fx-faint-focus-color: rgba(246,68,68,0);;");

            return false;
        }
    }
}
