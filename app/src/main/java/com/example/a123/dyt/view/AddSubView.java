package com.example.a123.dyt.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a123.dyt.R;

public class AddSubView extends LinearLayout implements View.OnClickListener {
    private TextView sub;
    private TextView some;
    private TextView add;
    int num=1;

    public AddSubView(Context context) {
        this(context, null);
    }

    public AddSubView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(getContext(), R.layout.addsub_layout, this);
        sub= view.findViewById(R.id.sub);
        some = view.findViewById(R.id.some);
        add = view.findViewById(R.id.add);

        sub.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sub:
                ++num;
                some.setText(num+"");
                if(numberListener!=null){
                    numberListener.onNum(num);
                }
                break;
            case R.id.add:
                if(num>1){
                    --num;
                    some.setText(num+"");
                    if(numberListener!=null){
                        numberListener.onNum(num);
                    }
                }
                break;
        }
    }

    public void setNum(int num) {
        this.num = num;
        some.setText(num+"");
    }

    public int getNum() {
        return num;
    }

    public interface NumberListener{
        void onNum(int num);
    }
    private NumberListener numberListener;

    public void setNumberListener(NumberListener numberListener) {
        this.numberListener = numberListener;
    }
}
