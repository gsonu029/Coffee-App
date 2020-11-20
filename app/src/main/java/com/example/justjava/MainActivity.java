package com.example.justjava;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class  MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox b=(CheckBox) findViewById(R.id.check_box) ;
        boolean wh=b.isChecked();
         CheckBox c=(CheckBox) findViewById(R.id.choco_box);
         boolean choco=c.isChecked();
       //  Log.v("MainActivity" ,"box is checked -> "+wh);
        EditText namefield=(EditText) findViewById(R.id.name_fill);
        String name = namefield.getText().toString();
        int price=calculatePrice(choco,wh);
        String a=createOrderSummary(name,price,wh,choco);
        String cc []= {"karankhannaiiitp@gmail.com"};

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Coffee ordering to you");
        intent.putExtra(Intent.EXTRA_TEXT,a);
        intent.putExtra(Intent.EXTRA_CC, cc );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

//        displayMessage(a);
    }
     /*
     this is increment method that increses the value by 1
      */
     public void increment(View view) {
         if(quantity==100){
             Toast.makeText(this,"You can't have more than 100 cup of coffee",Toast.LENGTH_SHORT).show();
             return;
         }

         quantity = quantity + 1;

         display(quantity);
     }

    public void decrement(View view) {
         if(quantity<=1)
         {
             Toast toast=Toast.makeText(this,"You can't have less than 1 coffee", Toast.LENGTH_SHORT);
             toast.setGravity(Gravity.CENTER,0,0); //syntax for -> where the message should appear on the screen
             toast.show();
             return;
         }
         quantity = quantity - 1;
        display(quantity);
     }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
        /**
         * This method displays the given price on the screen.
         */

    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean choco,boolean whipped) {
        int baseprice=5;
        if(whipped)
            baseprice=baseprice+1;
        if(choco)
            baseprice=baseprice+2;
        int price = quantity * baseprice;
        return price;
    }
    /* create summary of order
    @return the summary as string
    @price is order of order price
     */
    private String createOrderSummary(String name, int price,boolean wh,boolean choco) {
        String a="Name: "+ name+ "\nQuantity: "+ quantity + "\nadd Whipped cream: " + wh ;
        a=a+"\nadd chocolate: "+choco;
        a=a+"\nTotal price: $"+price;
        a+="\nThanku You";
        return a;


    }
    /*
    what should happen when whipped cream check box is clicked

    method name onCheckboxClicked;
     */
    public void onCheckboxClicked(View view){
        boolean a=((CheckBox) view).isChecked();
        /*if(a)
        {
            System.out.print("hbhb");
        }
        else
            System.out.print("No"); */
    }
     /*
    what should happen when chocolate  checkbox is clicked

    method name ChocoCheckboxClicked;
     */
public void ChocoCheckboxClicked(View view){
    boolean c=((CheckBox) view).isChecked();

}

}
