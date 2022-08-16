package classespackage.stackAndQueue.catDogQueue;

import classespackage.Constants;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author swzhao
 * @date 2021/11/17 9:50 下午
 * @Discreption <> 猫狗队列
 */
public class CatDogQueue {

    private Queue<CatDogQ> catQ;
    private Queue<CatDogQ> dogQ;
    private Integer count;

    public CatDogQueue() {
        this.catQ = new LinkedList<>();
        this.dogQ = new LinkedList<>();
        this.count = 0;
    }

    /**
     * 添加一个动物
     * @param p
     */
    public void add(Pet p){
        if(Constants.CAT.equals(p.getType())){
            // 放一个猫
            this.catQ.add(new CatDogQ(count++, p));
        }else if(Constants.DOG.equals(p.getType())){
            this.dogQ.add(new CatDogQ(count++, p));
        }
    }

    /**
     * 弹出队列尾巴（count最小的pet）
     * @return
     */
    public Pet pollAll(){
        if(this.catQ.isEmpty() && dogQ.isEmpty()){
            throw new RuntimeException("队列已空");
        }else if(this.catQ.isEmpty()){
            return this.dogQ.poll().getP();
        }else if(this.dogQ.isEmpty()){
            return this.catQ.poll().getP();
        }else{
            // 猫狗都不空，则判断count最小的
            if(this.catQ.peek().getCount() < dogQ.peek().getCount()){
                return this.catQ.poll().getP();
            }else{
                return this.dogQ.poll().getP();
            }
        }
    }

    /**
     * 弹出狗
     * @return
     */
    public Pet pollDog(){
        if(this.dogQ.isEmpty()){
            throw new RuntimeException("狗队列为空");
        }else{
            return dogQ.poll().getP();
        }
    }

    /**
     * 弹出猫
     * @return
     */
    public Pet pollCat(){
        if(this.catQ.isEmpty()){
            throw new RuntimeException("猫队列为空");
        }else{
            return catQ.poll().getP();
        }
    }

    public boolean isEmpty(){
        return catQ.isEmpty() && dogQ.isEmpty();
    }

    public boolean isDogEmpty(){
        return dogQ.isEmpty();
    }

    public boolean isCatEmpty(){
        return catQ.isEmpty();
    }

    public Queue<CatDogQ> getCatQ() {
        return catQ;
    }

    public Queue<CatDogQ> getDogQ() {
        return dogQ;
    }

    public Integer getCount() {
        return count;
    }

    public static void main(String[] args) {
        // 猫狗猫猫狗
        Pet[] pets = {new Cat(Constants.CAT), new Dog(Constants.DOG), new Cat(Constants.CAT), new Cat(Constants.CAT), new Dog(Constants.DOG)};
        CatDogQueue catDogQueue1 = new CatDogQueue();
        // 先放三个
        for (int i = 0; i < pets.length-2; i++){
            catDogQueue1.add(pets[i]);
        }
        // 当前q
        System.out.printf("catQ : %s, dogQ : %s \n", catDogQueue1.getCatQ(), catDogQueue1.getDogQ());
        // polldog
        System.out.printf("polldog：%s\n", catDogQueue1.pollDog());
        System.out.printf("catQ : %s, dogQ : %s \n", catDogQueue1.getCatQ(), catDogQueue1.getDogQ());
        // pollCat
        System.out.printf("pollcat：%s\n", catDogQueue1.pollCat());
        System.out.printf("catQ : %s, dogQ : %s\n", catDogQueue1.getCatQ(), catDogQueue1.getDogQ());
        // 放入剩下两个
        for (int i = 3; i < pets.length; i++){
            catDogQueue1.add(pets[i]);
        }
        System.out.printf("catQ : %s, dogQ : %s\n", catDogQueue1.getCatQ(), catDogQueue1.getDogQ());
        System.out.printf("pollall：%s\n", catDogQueue1.pollAll());
        System.out.printf("catQ : %s, dogQ : %s\n", catDogQueue1.getCatQ(), catDogQueue1.getDogQ());
    }


}
