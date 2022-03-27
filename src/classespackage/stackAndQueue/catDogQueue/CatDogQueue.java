package classespackage.stackAndQueue.catDogQueue;

import classespackage.Constants;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author swzhao
 * @date 2021/11/17 9:50 下午
 * @Discreption <> 猫狗队列：定义两个队列，猫队列和狗队列，进入队列前需要对象带一个时间戳，
 */
public class CatDogQueue {

    private Queue<CatDogQ> catQ;
    private Queue<CatDogQ> dogQ;
    private Integer count;

    public CatDogQueue(Queue<CatDogQ> catQ, Queue<CatDogQ> dogQ, Integer count) {
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

}
