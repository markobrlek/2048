package com.example.miraz.a2048;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.lang.reflect.Array;
import java.util.Random;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout relativeLayout;
    ImageView kvadrat0;
    ImageView kvadrat1;
    ImageView kvadrat2;
    ImageView kvadrat3;
    ImageView kvadrat4;
    ImageView kvadrat5;
    ImageView kvadrat6;
    ImageView kvadrat7;
    ImageView kvadrat8;
    ImageView kvadrat9;
    ImageView kvadrat10;
    ImageView kvadrat11;
    ImageView kvadrat12;
    ImageView kvadrat13;
    ImageView kvadrat14;
    ImageView kvadrat15;
    ArrayList<ArrayList<Integer>> matrica;
    ArrayList<ImageView> kvadrati;
    ArrayList<Integer> images;
    RelativeLayout.LayoutParams parametri;
    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
        Random rand=new Random();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenheight = displayMetrics.heightPixels;
        int screenwidth = displayMetrics.widthPixels;
        float fscreenheight=(float)screenheight;
        float fscreenwidth=(float)screenwidth;
        parametri=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        int kvadratwidth=screenwidth/6;
        float fkvadratwidth=(float)kvadratwidth;
        if (screenheight>=screenwidth){
            parametri.width=kvadratwidth;
            parametri.height=kvadratwidth;
        }
        else{
            parametri.width=kvadratwidth;
            parametri.height=kvadratwidth;
        }
        images=new ArrayList<>();
        images.add(R.drawable.slika0);
        images.add(R.drawable.slika1);
        images.add(R.drawable.slika2);
        images.add(R.drawable.slika3);
        images.add(R.drawable.slika4);
        images.add(R.drawable.slika5);
        images.add(R.drawable.slika6);
        images.add(R.drawable.slika7);
        images.add(R.drawable.slika8);
        images.add(R.drawable.slika9);
        images.add(R.drawable.slika10);
        images.add(R.drawable.slika11);
        images.add(R.drawable.slika12);
        images.add(R.drawable.slika13);
        images.add(R.drawable.slika14);
        images.add(R.drawable.slika15);
        kvadrati=new ArrayList<>();
        kvadrat0=new ImageView(this);
        kvadrati.add(kvadrat0);
        kvadrat1=new ImageView(this);
        kvadrati.add(kvadrat1);
        kvadrat2=new ImageView(this);
        kvadrati.add(kvadrat2);
        kvadrat3=new ImageView(this);
        kvadrati.add(kvadrat3);
        kvadrat4=new ImageView(this);
        kvadrati.add(kvadrat4);
        kvadrat5=new ImageView(this);
        kvadrati.add(kvadrat5);
        kvadrat6=new ImageView(this);
        kvadrati.add(kvadrat6);
        kvadrat7=new ImageView(this);
        kvadrati.add(kvadrat7);
        kvadrat8=new ImageView(this);
        kvadrati.add(kvadrat8);
        kvadrat9=new ImageView(this);
        kvadrati.add(kvadrat9);
        kvadrat10=new ImageView(this);
        kvadrati.add(kvadrat10);
        kvadrat11=new ImageView(this);
        kvadrati.add(kvadrat11);
        kvadrat12=new ImageView(this);
        kvadrati.add(kvadrat12);
        kvadrat13=new ImageView(this);
        kvadrati.add(kvadrat13);
        kvadrat14=new ImageView(this);
        kvadrati.add(kvadrat14);
        kvadrat15=new ImageView(this);
        kvadrati.add(kvadrat15);
        for (int k=0;k<16;k++){
            kvadrati.get(k).setLayoutParams(parametri);
            kvadrati.get(k).setX((fscreenwidth/(float)6)+((k%4)*fkvadratwidth));
            kvadrati.get(k).setY((fscreenwidth/(float)6)+((k/4)*fkvadratwidth));
            relativeLayout.addView(kvadrati.get(k));
        }
        matrica=new ArrayList<>();
        for (int i=0;i<4;i++){
            ArrayList<Integer> row = new ArrayList<>();
            for (int j=0;j<4;j++) {
                row.add(0);
            }
            matrica.add(row);
        }
        int randomredni=rand.nextInt(16);
        int randompocetni=rand.nextInt(2)+1;
        mjenjajmatricu(randomredni,randompocetni);
        mjenjajslike();
        setContentView(relativeLayout);
    }
    public void mjenjajmatricu(int rednibroj,int vrijednost){
        int red=rednibroj/4;
        int stupac=rednibroj%4;
        matrica.get(red).set(stupac,vrijednost);
    }
    public int findvalue(int index){
        return (matrica.get(index/4).get(index%4));
    }
    public void mjenjajslike(){
        for (int i=0;i<16;i++){
            kvadrati.get(i).setImageResource(images.get(findvalue(i)));
        }
    }
    public void moverightall(){
        boolean spawn=false;
        for (int z=0;z<4;z++) {
            for (int i = 2; i >= 0; i--) {
                int pocetnibroj = matrica.get(z).get(i);
                if (pocetnibroj != 0) {
                    for (int j = 1; j + i < 4; j++) {
                        int desnibroj = matrica.get(z).get(i + j);
                        if (desnibroj == 0) {
                            matrica.get(z).set(i + j, pocetnibroj);
                            matrica.get(z).set(i + j - 1, 0);
                            spawn=true;
                        } else if (desnibroj == pocetnibroj) {
                            matrica.get(z).set(i + j, pocetnibroj + 1);
                            matrica.get(z).set(i + j - 1, 0);
                            spawn=true;
                            break;
                        }
                        else{
                            break;
                        }
                    }
                }

            }
        }
        if (spawn){
            spawnrandom();
        }
    }
    public void moveleftall() {
        boolean spawn=false;
        for (int z=0;z<4;z++) {
            for (int i = 1; i < 4; i++) {
                int pocetnibroj = matrica.get(z).get(i);
                if (pocetnibroj != 0) {
                    for (int j = 1; i - j >= 0; j++) {
                        int lijevibroj = matrica.get(z).get(i - j);
                        if (lijevibroj == 0) {
                            matrica.get(z).set(i - j, pocetnibroj);
                            matrica.get(z).set(i - j + 1, 0);
                            spawn=true;
                        } else if (pocetnibroj == lijevibroj) {
                            matrica.get(z).set(i - j, pocetnibroj + 1);
                            matrica.get(z).set(i - j + 1, 0);
                            spawn=true;
                            break;
                        }
                        else{
                            break;
                        }
                    }
                }
            }
        }
        if (spawn){
            spawnrandom();
        }
    }
    public void spawnrandom(){
        Random rand=new Random();
        int insertbroj=rand.nextInt(2)+1;
        ArrayList<ArrayList<Integer>> slobodnamjesta = new ArrayList<>();
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                if (matrica.get(i).get(j)==0){
                    ArrayList<Integer> privremena = new ArrayList<>();
                    privremena.add(i);
                    privremena.add(j);
                    slobodnamjesta.add(privremena);
                }
            }
        }
        int rednimjesto=rand.nextInt(slobodnamjesta.size());
        matrica.get(slobodnamjesta.get(rednimjesto).get(0)).set(slobodnamjesta.get(rednimjesto).get(1),insertbroj);
    }
    public void moveupall(){
        boolean spawn=false;
        for (int i=1;i<4;i++){
            for (int j=0;j<4;j++){
                int pocetnibroj=matrica.get(i).get(j);
                if (pocetnibroj!=0){
                    for (int k=1;i-k>=0;k++){
                        int gornjibroj=matrica.get(i-k).get(j);
                        if (gornjibroj==0){
                            matrica.get(i-k).set(j,pocetnibroj);
                            matrica.get(i-k+1).set(j,0);
                            spawn=true;
                        }
                        else if (pocetnibroj==gornjibroj){
                            matrica.get(i-k).set(j,pocetnibroj+1);
                            matrica.get(i-k+1).set(j,0);
                            spawn=true;
                            break;
                        }
                        else{
                            break;
                        }
                    }
                }
            }
        }
        if (spawn){
            spawnrandom();
        }
    }
    public void movedownall(){
        boolean spawn=false;
        for (int i=2;i>=0;i--){
            for (int j=0;j<4;j++){
                int pocetnibroj=matrica.get(i).get(j);
                if (pocetnibroj!=0){
                    for (int k=1;i+k<4;k++){
                        int donjibroj=matrica.get(i+k).get(j);
                        if (donjibroj==0){
                            matrica.get(i+k).set(j,pocetnibroj);
                            matrica.get(i+k-1).set(j,0);
                            spawn=true;
                        }
                        else if (pocetnibroj==donjibroj){
                            matrica.get(i+k).set(j,pocetnibroj+1);
                            matrica.get(i+k-1).set(j,0);
                            spawn=true;
                            break;
                        }
                        else{
                            break;
                        }
                    }
                }
            }
        }
        if (spawn){
            spawnrandom();
        }
    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1+200<x2){
                    moverightall();
                    mjenjajslike();
                //swipe right
                }else if(x1>x2+200){
                    moveleftall();
                    mjenjajslike();
                    //swipe left
                }else if(y1+200<y2){
                    movedownall();
                    mjenjajslike();
                    //swipe down
                }else if(y1>y2+200){
                    moveupall();
                    mjenjajslike();
                    //swipe up
                }
            break;
        }
        return false;
    }

}
