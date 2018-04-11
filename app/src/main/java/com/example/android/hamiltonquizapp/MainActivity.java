package com.example.android.hamiltonquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MainActivity extends AppCompatActivity {

    private static int numQuestions = 5;
    private Queue<CheckBox> boxesCheckedQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boxesCheckedQueue = new LinkedList<CheckBox>();
    }

    /**
     * The method returns the result of question #1
     * @return 1 if question was correct, 0 if incorrect
     */
    private int getQ1Result(){
        return ((RadioButton)findViewById(R.id.q1_answer_radio)).isChecked()? 1:0;
    }

    /**
     * The method returns the result of question #2
     * @return 1 if question was correct, 0 if incorrect
     */
    private int getQ2Result(){
        return ((EditText) findViewById(R.id.q2_answer_text)).getText().toString().toLowerCase().equals("shot")? 1:0;
    }

    /**
     * The method returns the result of question #3
     * @return 1 if question was correct, 0 if incorrect
     */
    private int getQ3Result(){
        return ((RadioButton) findViewById(R.id.q3_answer_radio)).isChecked()? 1:0;
    }

    /**
     * The method returns the result of question #4
     * @return 1 if question was correct, 0 if incorrect
     */
    private int getQ4Result(){

        if(((CheckBox) findViewById(R.id.q4_answer_wpm_check1)).isChecked() &&
                ((CheckBox) findViewById(R.id.q4_answer_whitehouse_check2)).isChecked() &&
                ((CheckBox) findViewById(R.id.q4_answer_diversecast_check4)).isChecked() &&
                !((CheckBox) findViewById(R.id.q4_answer_mirandarole_check3)).isChecked()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * The method returns the result of question #5
     * @return 1 if question was correct, 0 if incorrect
     */
    private int getQ5Result(){

        if(((CheckBox) findViewById(R.id.q5_answer_hamilton_check1)).isChecked() &&
                ((CheckBox) findViewById(R.id.q5_answer_jefferson_check3)).isChecked() &&
                !((CheckBox) findViewById(R.id.q5_answer_washington_check2)).isChecked() &&
                !((CheckBox) findViewById(R.id.q5_answer_burr_check4)).isChecked()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * This method will allow only two checkboxes to be checked at a time. When a 3rd is checked, the 1st checked will be unchecked.
     * @param view is the checkbox that was checked or unchecked
     */
    public void allowOnlyTwo(View view) {

        CheckBox checkBox = (CheckBox) view;

        // If this checkbox is checked, add to the queue
        if(checkBox.isChecked()) {

            // If 2 are checked, uncheck the box that was 1st checked of the 2
            if(boxesCheckedQueue.size() == 2) {
                CheckBox firstChecked = boxesCheckedQueue.poll();
                firstChecked.setChecked(false);
            }
            // Add new checked box onto queue
            boxesCheckedQueue.add((CheckBox) checkBox);
        }
        // If this checkbox is unchecked, remove it from the queue
        else {
            boxesCheckedQueue.remove(checkBox);
        }

    }

    /**
     * This method is called when the Submit button is clicked
     * @param view
     */
    public void gradeQuiz (View view) {
        int numCorrect = 0;

        numCorrect += getQ1Result() + getQ2Result() + getQ3Result() + getQ4Result() + getQ5Result();
        String toastScoreMessage = "You got " + numCorrect + " out of " + numQuestions + " correct.";

        // Show a toast with the number correct out of the number of questions
        Toast.makeText(this, toastScoreMessage, Toast.LENGTH_SHORT).show();

        //TODO Reset answers?

    }
}
