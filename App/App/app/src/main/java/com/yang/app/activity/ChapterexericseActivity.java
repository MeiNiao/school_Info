package com.yang.app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.LogUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.yang.app.R;
import com.yang.app.javaBean.Answer;

public class ChapterexericseActivity extends AppCompatActivity {

    @InjectView(R.id.tv_index)
    TextView tvIndex;
    @InjectView(R.id.tv_types)
    TextView tvTypes;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_a)
    ImageView ivA;
    @InjectView(R.id.tv_a)
    TextView tvA;
    @InjectView(R.id.lin_a)
    LinearLayout linA;
    @InjectView(R.id.iv_b)
    ImageView ivB;
    @InjectView(R.id.tv_b)
    TextView tvB;
    @InjectView(R.id.lin_b)
    LinearLayout linB;
    @InjectView(R.id.iv_c)
    ImageView ivC;
    @InjectView(R.id.tv_c)
    TextView tvC;
    @InjectView(R.id.lin_c)
    LinearLayout linC;
    @InjectView(R.id.iv_d)
    ImageView ivD;
    @InjectView(R.id.tv_d)
    TextView tvD;
    @InjectView(R.id.lin_d)
    LinearLayout linD;
    @InjectView(R.id.tv_result)
    TextView tvResult;
    @InjectView(R.id.tv_detail)
    TextView tvDetail;
    @InjectView(R.id.lin_daan_answer)
    LinearLayout linDaanAnswer;
    @InjectView(R.id.lin_navigation_back)
    LinearLayout linNavigationBack;
    @InjectView(R.id.lin_answer)
    LinearLayout linAnswer;
    @InjectView(R.id.tv_current_number)
    TextView tvCurrentNumber;
    @InjectView(R.id.lin_bt_unstar)
    LinearLayout linBtUnstar;
    @InjectView(R.id.lin_navigation_forward)
    LinearLayout linNavigationForward;
    //拿到所有数据

    //索引,现在做到第几题了
    private int index;
    private List<Answer> list;
    //正确的题目个数
    private int rightResult;
    //错误的题目个数
    private int wrongResult;

    /**
     * 1.第一步我们要从数据库拿回题目信息，装在list里面
     * 2.第二步我们从list里去获取answer对象，来渲染我的textview
     * 3.第三步--->上一题 ，下一题-->list，通过里面的get(position)-->Answer对象
     * 4.第四步答案排错
     * 1>首先要找到我们的正确答案
     * 2>点击的答案，传递一个选择的答案，A
     * 3>通过选择的答案和我的正确答案进行比较
     * 4>渲染我们该有的界面展示
     * 5.对做过的题目进行数据库填充
     * ---->reply 进行我做过的题统计,通过我的数据库框架改变我的数据库内容
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapterexericse);
        ButterKnife.inject(this);
        list= DataSupport.findAll(Answer.class);
        //一开始我会去清除reply字段，让reply变成0；
        clearReply();
        //从数据库里面拿数据
        getDatas();
    }

    //初始化

    /**
     * 清除reply
     */
    private void clearReply() {
        for (int i = 0; i < list.size(); i++) {
            //list.get(i)代表一个answer对象
            Answer answer = list.get(i);
            //设置值
            answer.setReply("0");
            //通过id进行每个reply的值变成0
            answer.updateAll("id = ?" ,list.get(i).getId());
        }
    }
    /**
     * 从数据库里面拿数据，渲染
     */
    private void getDatas() {

        //第一条数据如何获取
        Answer answer = list.get(index);


        //当前做到第几题？
        tvCurrentNumber.setText((index + 1) + "/" + list.size());
        //题目编号渲染
        tvIndex.setText("第" + answer.getId() + "题");
        //题目类型
        tvTypes.setText(answer.getTypes());
        //题目信息
        tvTitle.setText(answer.getTimu_title());
        //选择选择abcd
        tvA.setText("A. " + answer.getTimu1());
        tvB.setText("B. " + answer.getTimu2());
        tvC.setText("C. " + answer.getTimu3());
        tvD.setText("D. " + answer.getTimu4());
        //渲染解析
        tvDetail.setText(answer.getDetail());

        //这里来判断这道题是否做过
        if ("0".equals(answer.getReply())) {
            //这道题没做过

        }
        else{
            //这道题做过
            String hisReply = answer.getReply();//之前做过的答案
            getDaanCom(hisReply);//数据渲染
        }
    }


    /**
     *
     * 获取答案
     */
    private void getDaan(String choice) {

        Answer answer = list.get(index);

        answer.setReply(choice);
        //更新数据库reply字段
        //？是sql注入，防止 修改数据库结构
        answer.updateAll("id = ?",answer.getId());


        //定义一个字符串
        String daan;

        if (EmptyUtils.isNotEmpty(answer.getDaan1())) {
            daan = answer.getDaan1();
        } else if (EmptyUtils.isNotEmpty(answer.getDaan2())) {
            daan = answer.getDaan2();
        } else if (EmptyUtils.isNotEmpty(answer.getDaan3())) {
            daan = answer.getDaan3();
        } else if (EmptyUtils.isNotEmpty(answer.getDaan4())) {
            daan = answer.getDaan4();
        } else {
            daan = "";
        }
        LogUtils.a("===", "正确答案是：" + daan);
        //判断选择的答案是否我的正确答案一致
        if (choice.equals(daan)) {
            //做我选择正确的界面渲染
            initRight(choice);
            rightResult++;//自增长1个

        } else {
            //做我选择错误的界面渲染
            initWrong(choice);
            initRight(daan);
            wrongResult++;
        }
        //做完一题过后不能再让他去点击
        clickFlag(false);
        //结果进行统计
        tvResult.setVisibility(View.VISIBLE);
        tvResult.setText("共答"+(rightResult+wrongResult)+"题,答对"+rightResult+"题,答错"+wrongResult+"题,正确率"+ (rightResult*100/(rightResult+wrongResult)) +"%");
    }
    /**
     * 渲染做过的题目的界面（不需要统计）
     */
    private void getDaanCom(String choice) {
        //做完一题过后不能再让他去点击
        clickFlag(false);
        Answer answer = list.get(index);

        answer.setReply(choice);
        //更新数据库reply字段
        //？是sql注入，防止 修改数据库结构
        answer.updateAll("id = ?",answer.getId());

        //定义一个字符串
        String daan;

        if (EmptyUtils.isNotEmpty(answer.getDaan1())) {
            daan = answer.getDaan1();
        } else if (EmptyUtils.isNotEmpty(answer.getDaan2())) {
            daan = answer.getDaan2();
        } else if (EmptyUtils.isNotEmpty(answer.getDaan3())) {
            daan = answer.getDaan3();
        } else if (EmptyUtils.isNotEmpty(answer.getDaan4())) {
            daan = answer.getDaan4();
        } else {
            daan = "";
        }
        LogUtils.a("===", "正确答案是：" + daan);
        //判断选择的答案是否我的正确答案一致
        if (choice.equals(daan)) {
            //做我选择正确的界面渲染
            initRight(choice);
        } else {
            //做我选择错误的界面渲染
            initWrong(choice);
            initRight(daan);
        }

    }

    /**
     * 设置是否可以点击四个选择按钮
     */
    private void clickFlag(boolean b) {
        linA.setClickable(b);
        linB.setClickable(b);
        linC.setClickable(b);
        linD.setClickable(b);
    }

    /**
     * 渲染错误的界面
     */
    private void initWrong(String choice) {

        if ("A".equals(choice)) {
            ivA.setImageResource(R.drawable.wrong);
            linA.setBackgroundColor(getResources().getColor(R.color.wrong));
        } else if ("B".equals(choice)) {
            ivB.setImageResource(R.drawable.wrong);
            linB.setBackgroundColor(getResources().getColor(R.color.wrong));
        } else if ("C".equals(choice)) {
            ivC.setImageResource(R.drawable.wrong);
            linC.setBackgroundColor(getResources().getColor(R.color.wrong));
        } else if ("D".equals(choice)) {
            ivD.setImageResource(R.drawable.wrong);
            linD.setBackgroundColor(getResources().getColor(R.color.wrong));
        }

    }

    /**
     * 渲染正确的界面
     */
    private void initRight(String choice) {
        if ("A".equals(choice)) {
            ivA.setImageResource(R.drawable.right);
            linA.setBackgroundColor(getResources().getColor(R.color.right));
        } else if ("B".equals(choice)) {
            ivB.setImageResource(R.drawable.right);
            linB.setBackgroundColor(getResources().getColor(R.color.right));
        } else if ("C".equals(choice)) {
            ivC.setImageResource(R.drawable.right);
            linC.setBackgroundColor(getResources().getColor(R.color.right));
        } else if ("D".equals(choice)) {
            ivD.setImageResource(R.drawable.right);
            linD.setBackgroundColor(getResources().getColor(R.color.right));
        }


    }

    @OnClick({R.id.lin_a, R.id.lin_b, R.id.lin_c, R.id.lin_d, R.id.lin_daan_answer, R.id.lin_navigation_back, R.id.lin_answer, R.id.lin_bt_unstar, R.id.lin_navigation_forward})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //上一题
            case R.id.lin_navigation_back:
                //如果我这题为第一题，那么return出去
                if (index == 0) {
                    Toast.makeText(this, "已经是第一题了", Toast.LENGTH_SHORT).show();
                    return;
                }
                clearLin();
                index--;
                getDatas();
                break;
            //下一题
            case R.id.lin_navigation_forward:
                //如果我这题为最后一题，那么return出去
                if (index == list.size() - 1) {
                    Toast.makeText(this, "已经是最后一题了", Toast.LENGTH_SHORT).show();
                    return;
                }
                //清除选择的界面
                clearLin();

                index++;
                //进行数据渲染
                getDatas();
                break;
            //查看题解
            case R.id.lin_answer:
                linDaanAnswer.setVisibility(View.VISIBLE);
                break;
            //点击A
            case R.id.lin_a:
                //获取答案
                getDaan("A");
                break;
            //点击B
            case R.id.lin_b:
                getDaan("B");
                break;
            //点击C
            case R.id.lin_c:
                getDaan("C");
                break;
            //点击D
            case R.id.lin_d:
                getDaan("D");
                break;
        }
    }

    /**
     * 清除已选展示出来的界面
     */
    private void clearLin() {
        //题解的消失
        linDaanAnswer.setVisibility(View.GONE);
        //结果xiaoshi
        tvResult.setVisibility(View.GONE);

        linA.setBackgroundColor(getResources().getColor(R.color.choice));
        linB.setBackgroundColor(getResources().getColor(R.color.choice));
        linC.setBackgroundColor(getResources().getColor(R.color.choice));
        linD.setBackgroundColor(getResources().getColor(R.color.choice));

        ivA.setImageResource(R.drawable.defaults);
        ivB.setImageResource(R.drawable.defaults);
        ivC.setImageResource(R.drawable.defaults);
        ivD.setImageResource(R.drawable.defaults);

        //做完一题过后不能再让他去点击
        clickFlag(true);

    }
}
