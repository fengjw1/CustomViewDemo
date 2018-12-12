package com.fengjw.customviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fengjw.customviewdemo.ui.ArcActivity;
import com.fengjw.customviewdemo.ui.BezierCurveActivity;
import com.fengjw.customviewdemo.ui.CanvasOperationActivity;
import com.fengjw.customviewdemo.ui.CheckViewActivity;
import com.fengjw.customviewdemo.ui.CircleActivity;
import com.fengjw.customviewdemo.ui.LineActivity;
import com.fengjw.customviewdemo.ui.OvalActivity;
import com.fengjw.customviewdemo.ui.PathActivity;
import com.fengjw.customviewdemo.ui.PieChartActivity;
import com.fengjw.customviewdemo.ui.PointActivity;
import com.fengjw.customviewdemo.ui.RectActivity;
import com.fengjw.customviewdemo.ui.RoundRectActivity;
import com.fengjw.customviewdemo.ui.ThirdBezierCurveActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnPoint;
    private Button mBtnLine;
    private Button mBtnRect;
    private Button mBtnRountrect;
    private Button mBtnOval;
    private Button mBtnCircle;
    private Button mBtnArc;
    private Button mBtnPiechart;
    private Button mBtnCanvasoperation;
    private Button mBtnCheckview;
    private Button mBtnPath;
    private Button mBtnBeziercurve;
    private Button mBtnThreebeziercurve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

//        ArrayList<PieData> datas = new ArrayList<>();
//        PieData pieData = new PieData("sloop", 60);
//        PieData pieData2 = new PieData("sloop", 30);
//        PieData pieData3 = new PieData("sloop", 40);
//        PieData pieData4 = new PieData("sloop", 20);
//        PieData pieData5 = new PieData("sloop", 20);
//        datas.add(pieData);
//        datas.add(pieData2);
//        datas.add(pieData3);
//        datas.add(pieData4);
//        datas.add(pieData5);
//        view.setData(datas);
    }

    private void initView() {
        mBtnPoint = (Button) findViewById(R.id.point_btn);
        mBtnPoint.setOnClickListener(this);
        mBtnLine = (Button) findViewById(R.id.line_btn);
        mBtnLine.setOnClickListener(this);
        mBtnRect = (Button) findViewById(R.id.rect_btn);
        mBtnRect.setOnClickListener(this);
        mBtnRountrect = (Button) findViewById(R.id.rountrect_btn);
        mBtnRountrect.setOnClickListener(this);
        mBtnOval = (Button) findViewById(R.id.oval_btn);
        mBtnOval.setOnClickListener(this);
        mBtnCircle = (Button) findViewById(R.id.circle_btn);
        mBtnCircle.setOnClickListener(this);
        mBtnArc = (Button) findViewById(R.id.Arc_btn);
        mBtnArc.setOnClickListener(this);
        mBtnPiechart = (Button) findViewById(R.id.piechart_btn);
        mBtnPiechart.setOnClickListener(this);
        mBtnCanvasoperation = (Button) findViewById(R.id.canvasoperation_btn);
        mBtnCanvasoperation.setOnClickListener(this);
        mBtnCheckview = (Button) findViewById(R.id.checkview_btn);
        mBtnCheckview.setOnClickListener(this);
        mBtnPath = (Button) findViewById(R.id.path_btn);
        mBtnPath.setOnClickListener(this);
        mBtnBeziercurve = (Button) findViewById(R.id.beziercurve_btn);
        mBtnBeziercurve.setOnClickListener(this);
        mBtnThreebeziercurve = (Button) findViewById(R.id.threebeziercurve_btn);
        mBtnThreebeziercurve.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.point_btn:
                // TODO 18/12/10
                intent = new Intent(this, PointActivity.class);
                startActivity(intent);
                break;
            case R.id.line_btn:// TODO 18/12/10
                intent = new Intent(this, LineActivity.class);
                startActivity(intent);
                break;
            case R.id.rect_btn:// TODO 18/12/10
                intent = new Intent(this, RectActivity.class);
                startActivity(intent);
                break;
            case R.id.rountrect_btn:// TODO 18/12/10
                intent = new Intent(this, RoundRectActivity.class);
                startActivity(intent);
                break;
            case R.id.oval_btn:// TODO 18/12/10
                intent = new Intent(this, OvalActivity.class);
                startActivity(intent);
                break;
            case R.id.circle_btn:// TODO 18/12/10
                intent = new Intent(this, CircleActivity.class);
                startActivity(intent);
                break;
            case R.id.Arc_btn:// TODO 18/12/10
                intent = new Intent(this, ArcActivity.class);
                startActivity(intent);
                break;
            case R.id.piechart_btn:// TODO 18/12/11
                intent = new Intent(this, PieChartActivity.class);
                startActivity(intent);
                break;
            case R.id.canvasoperation_btn:// TODO 18/12/11
                intent = new Intent(this, CanvasOperationActivity.class);
                startActivity(intent);
                break;
            case R.id.checkview_btn:// TODO 18/12/11
                intent = new Intent(this, CheckViewActivity.class);
                startActivity(intent);
                break;
            case R.id.path_btn:// TODO 18/12/12
                intent = new Intent(this, PathActivity.class);
                startActivity(intent);
                break;
            case R.id.beziercurve_btn:// TODO 18/12/12
                intent = new Intent(this, BezierCurveActivity.class);
                startActivity(intent);
                break;
            case R.id.threebeziercurve_btn:// TODO 18/12/12
                intent = new Intent(this, ThirdBezierCurveActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
