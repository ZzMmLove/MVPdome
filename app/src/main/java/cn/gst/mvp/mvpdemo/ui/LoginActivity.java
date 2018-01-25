package cn.gst.mvp.mvpdemo.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.laojiang.retrofithttp.weight.downfilesutils.FinalDownFiles;
import com.laojiang.retrofithttp.weight.downfilesutils.action.FinalDownFileResult;
import com.laojiang.retrofithttp.weight.downfilesutils.downfiles.DownInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.gst.mvp.mvpdemo.R;
import cn.gst.mvp.mvpdemo.presenter.UserLoginPresenter;
import cn.gst.mvp.mvpdemo.bean.User;
import cn.gst.mvp.mvpdemo.util.MyCountDownTimer;
import cn.gst.mvp.mvpdemo.view.AnimationBottom;
import cn.gst.mvp.mvpdemo.view.FallingView;
import stfalcon.universalpickerdialog.UniversalPickerDialog;


/**
 * 首页面
 * Created by Administrator on 4/1 0001.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView , UniversalPickerDialog.OnPickListener{

    private EditText mUsername;
    private EditText mPassword;

    private TextView downTime;

    private Button mLogin;

    private Button mClear;

    private Button choose;

    private Button animation, pdf, download;

    private Button beziewaveview;

    private Button shoppingCar;

    private Button fallingBtn;

    private Button btnWebView;

    private ProgressBar mProgressBar;
    private TextView textView;
    private Button dialog;
    private Button startView;
    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private String[] arry = new String[]{"C","C","C","C"};

    // var myJSONString = JSON.stringify(myJSON); var myEscapedJSONString = myJSONString.replace(/\\n/g, "\\n") .replace(/\\r/g, "\\r")

   // String htmlText = "2017年4月11日下午，《百年巨匠》书法篇摄制组采访中国书法家协会名誉主席、《百年巨匠》书法篇总顾问、《百年巨匠》国内宣传片代言人沈鹏。沈鹏与《百年巨匠》颇有渊源。早在2015年9月16日，沈鹏在钓鱼台国宾馆接受《百年巨匠》书法篇摄制组采访，表示于右任、沈尹默、林散之、沙孟海、赵朴初、启功，是20世纪中国书法的杰出代表，并谈到他与赵朴初、启功先生的交往，还为《百年巨匠》题字。2016年8月，沈鹏以代言人身份参与了《百年巨匠》宣传片拍摄，在录制现场谈到《百年巨匠》时由衷地感叹：“他们是真正的艺术大师。”2017年1月16日下午，中国书法家协会名誉主席、《百年巨匠》书法篇总顾问、《百年巨匠》宣传片代言人沈鹏，接受《百年巨匠》书法篇第二部总导演寒冰的采访，畅谈“马背书法家”舒同的艺术人生。<br /><img src='http://p1.pstatp.com/large/1c5c00013b7d9c56fcd2' /><br />人物简介<br /><img src='http://p3.pstatp.com/large/1c5b00029c24278e7659' /><br />中国书法家协会名誉主席、《百年巨匠》书法篇总顾问沈鹏<br />沈鹏，别署介居主，著名书法家、诗人、美术评论家、编辑出版家，首批国务院有突出贡献专家。<br />1931年9月1日出生于江苏江阴一个教师家庭，先后就读于城南小学（外祖父王逸旦捐资首创）、南菁中学（外叔公王心农曾任校长）。十五岁时发起创办文学刊物《曙光》并任主编。十七岁入大学攻读文学，投身爱国学生运动，后转学新闻（北京新闻学校）。十九岁起，长年从事美术出版工作，同时撰写评论。四十岁以后投入诗词、书法创作。历任人民美术出版社编辑、副总编、编审、编审委员会主任，中国书法家协会副主席、代主席、主席。现为全国政协委员、中央文史馆馆员、中国文联荣誉委员、中国书法家协会名誉主席、中华诗词学会名誉会长、中国国家画院书法篆刻院院长、中国美术出版总社顾问，并兼任多种社会职务。<br />书法精行草、善隶楷。老年致力于书法高研人才培养，制定并贯彻“十六字方针”（宏扬原创，尊重个性，书内书外，艺道并进）。提出中国书法可持续发展的理念。古典诗词创作发表逾千首。专注美术、书法理论和实践研究，撰写评论文章百余篇。先后出版诗词选集《三馀吟草》《三馀续吟》《三馀再吟》，评论文集《书画评论》《沈鹏书画谈》《沈鹏书画续谈》《书法本体与多元》及各类书法作品集凡四十余种。荣获“造型艺术成就奖”“中国书法兰亭奖”终身成就奖、“全国第三届华夏诗词奖”荣誉奖、“中华艺文奖”终身成就奖、“中华诗词”荣誉奖、联合国Academy“世界和平艺术大奖”等，并获得“十大感动诗网人物”“卓有成就的美术史论家”“编辑名家”“爱心大使”“中国十大慈善家”“中国十大魅力英才”等荣誉称号。热心公益事业，长期大量捐款，捐赠名人字画、文物等。<br />沈鹏谈《百年巨匠》<br />2016年8月，中国书协名誉主席、《百年巨匠》书法篇总顾问沈鹏在百忙之中来到银谷艺术馆，录制《百年巨匠》国内宣传片。在录制现场他们反复试镜、录音，不厌其烦地拍摄，声情并茂地表达对大师巨匠的尊崇之情和对《百年巨匠》的寄语，沈鹏谈到《百年巨匠》时由衷地感叹：“他们是真正的艺术大师。”<br /><img src='http://p3.pstatp.com/large/1c5f0000208dd640d0c1' /><br />沈鹏为《百年巨匠》宣传片代言，并寄语诸位巨匠：<br />他们是真正的艺术大师。<br /><img src='http://p3.pstatp.com/large/1b8300029a8816441b39' /><br /><img src='http://p3.pstatp.com/large/1c60000004cbee4aa7e9' /><b";

 /*   String str = htmlText.replace(/&lt;/g, "<").replace("/&gt;/g",">");
    String mStr = str.replace(/<br\\/>/g, "\r\n");*/



    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        intView();
        new MyCountDownTimer(this,
                            172800000,//1000*60*60*24*2  时间戳
                            1000,          // 每隔1秒
                            "dd:HH:mm:ss",   //时间格式化类型
                            R.drawable.timer_shape,   //显示TextView的样式
                            downTime)            //TextView 组件
                .setTimerPadding(10,10,10,10)
                .setTimerTextColor(Color.BLUE)
                .setTimerTextSize(20)
                .setTimerGapColor(Color.BLACK)
                .start();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void intView() {
        list1.add("A");
        list1.add("A");
        list1.add("A");
        list1.add("A");

        list2.add("B");
        list2.add("B");
        list2.add("B");
        list2.add("B");
        Html.ImageGetter imageGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                return null;
            }
        };

        downTime = (TextView) findViewById(R.id.downtime);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login);
        mClear = (Button) findViewById(R.id.clear);
        textView = (TextView) findViewById(R.id.text);
        dialog = (Button) findViewById(R.id.dialog);
        choose = (Button) findViewById(R.id.choose);
        animation = (Button) findViewById(R.id.animation);
        pdf = (Button) findViewById(R.id.pdf);
        download = (Button) findViewById(R.id.download);
        beziewaveview = (Button) findViewById(R.id.beziewaveview);
        shoppingCar = (Button) findViewById(R.id.shoppingcar);
        startView = (Button) findViewById(R.id.activity_start_view_group);
        fallingBtn = (Button) findViewById(R.id.activity_falling);
        btnWebView = (Button) findViewById(R.id.activity_webview);



        //textView.setText(Html.fromHtml(htmlText));

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.login();
            }
        });

        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.clear();
            }
        });

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UniversalPickerDialog.Builder(LoginActivity.this)
                        .setTitle("Holle")
                        .setBackgroundColor(Color.rgb(111, 112, 111))
                        .setListener(LoginActivity.this)
                        .setInputs(
                                new UniversalPickerDialog.Input(0, arry),
                                new UniversalPickerDialog.Input(0, arry),
                                new UniversalPickerDialog.Input(1, arry)
                        )
                        .show();
            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SencondActivity.class));
            }
        });

        animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, AnimationButtonActivity.class));
            }
        });

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PDFActivity.class));
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download();
            }
        });

        beziewaveview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, TestActivity.class));
            }
        });

        shoppingCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ShoppingCarActivity.class));
            }
        });
        //利用GradientDrawable来动态设置背景颜色
        GradientDrawable gradientDrawable = (GradientDrawable) startView.getBackground();
        gradientDrawable.setColor(Color.GRAY);
        startView.setBackground(gradientDrawable);
        startView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, StartViewGroupActivity.class));
            }
        });

        fallingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, FallingActivity.class));
            }
        });
        btnWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, WebViewActivity.class));
                startActivity(new Intent(LoginActivity.this, WebViewAndJSActivity.class));
            }
        });

    }

    private void download() {
        new File(

                "/data/data/" + getPackageName() + "/handouts").mkdir();
        File destinationFile = new File("/data/data/" + getPackageName() + "/handouts/" + "note01.ppt");
        String filepath = destinationFile.toString();
        String[] downurl = new String[]{"http://shuoke360.cn/Public/Uploads/Note/20170925/59c8840e7c231.ppt"};
                new FinalDownFiles(true, this,downurl[0],
                        filepath,                          //Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"note"+0+".apk"
                        new FinalDownFileResult()){
                    @Override
                    public void onStart() {
                        super.onStart();
                        Log.e("TAG", "onStart");
                    }

                    @Override
                    public void onNext(DownInfo baseDownEntity) {
                        super.onNext(baseDownEntity);
                        Log.e("TAG", "onStart"+baseDownEntity.getReadLength());
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        Log.e("TAG", "onComplete");
                    }

                    @Override
                    public void onPuase() {
                        super.onPuase();
                        Log.e("TAG", "onPuase");
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        Log.e("TAG", "onStop");
                    }
                };
    }

    @Override
    public String getUsername() {
        return mUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mPassword.getText().toString().trim();
    }

    @Override
    public void showLodding() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLodding() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, "zhongmo登录成功！！", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "登录失败！！！", Toast.LENGTH_LONG).show();
    }

    @Override
    public void clearUsername() {
        mUsername.setText("");
    }

    @Override
    public void clearPassword() {
        mPassword.setText("");
    }

    @Override
    public void onPick(int[] selectedValues, int key) {
        Toast.makeText(this,""+selectedValues[key], Toast.LENGTH_LONG).show();
    }
}
