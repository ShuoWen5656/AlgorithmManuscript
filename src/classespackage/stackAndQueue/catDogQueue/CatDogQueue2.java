package classespackage.stackAndQueue.catDogQueue;

import classespackage.Constants;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author swzhao
 * @data 2022/8/16 21:30
 * @Discreption <> 猫狗队列方案二
 * 纳管两个队列，当一个队列弹出为空的时候，轮换开关变
 * 出的时候判断是否是所要求的，如果是所要求的弹出即可，如果不是则进入另一个队列
 */
public class CatDogQueue2 {


    /**
     * 轮询队列1
     */
    private Queue<Pet> queue1;

    /**
     * 轮询队列2
     */
    private Queue<Pet> queue2;

    /**
     * 是否是队列1的轮次，add只往当前轮次的queue里加入
     */
    boolean isQueue1;

    /**
     * 猫的个数
     */
    int catSize;
    /**
     * 狗的个数
     */
    int dogSize;

    public CatDogQueue2() {
        // q1
        this.queue1 = new LinkedList<>();
        // q2
        this.queue2 = new LinkedList<>();
        // 默认q1轮班
        this.isQueue1 = true;
        this.catSize = Constants.NUM_0;
        this.dogSize = Constants.NUM_0;
    }

    /**
     * 添加一个pet
     * @param pet
     */
    public void add(Pet pet){
        if (isQueue1){
            queue1.add(pet);
        }else{
            queue2.add(pet);
        }
        if (Constants.CAT.equals(pet.getType())){
            catSize++;
        }else {
            dogSize++;
        }
    }

    /**
     * 弹出一只猫
     * @return
     */
    public Cat pollCat(){
        if (isCatEmpty()){
            throw new RuntimeException("猫队列空");
        }
        // 默认q1当值
        Queue<Pet> dutyq = queue1;
        Queue<Pet> other = queue2;
        if (!isQueue1){
            // q2当值
            dutyq = queue2;
            other = queue1;
        }
        // 先看other是否是猫队列
         if (!other.isEmpty() && Constants.CAT.equals(other.peek().getType())){
             dogSize--;
             return (Cat) other.poll();
        }else {
             dogSize--;
             return (Cat) getFirstPet(Constants.CAT, dutyq, other);
        }
    }

    /**
     * 从当值中获取一个制定的pet
     * @param type
     * @param dutyq
     * @param other
     */
    private Pet getFirstPet(String type, Queue<Pet> dutyq, Queue<Pet> other) {
        while (!dutyq.isEmpty()){
            Pet poll = dutyq.poll();
            if (type.equals(poll.getType())){
                // 获取到了
                // 判断是否需要更换当值
                if (dutyq.isEmpty()){
                    isQueue1 = false;
                }
                return poll;
            }else {
                // 没获取到就放入非当值中
                other.offer(poll);
            }
        }
        return null;
    }

    /**
     * 弹出一只狗
     * @return
     */
    public Dog pollDog(){
        if (isDogEmpty()){
            throw new RuntimeException("狗队列空");
        }
        // 默认q1当值
        Queue<Pet> dutyq = queue1;
        Queue<Pet> other = queue2;
        if (!isQueue1){
            // q2当值
            dutyq = queue2;
            other = queue1;
        }
        // 先看other是否是猫队列
        if (!other.isEmpty() && Constants.DOG.equals(other.peek().getType())){
            dogSize--;
            return (Dog) other.poll();
        }else {
            dogSize--;
            return (Dog) getFirstPet(Constants.DOG, dutyq, other);
        }
    }


    /**
     * 弹出一只pet
     * @return
     */
    public Pet pollAll(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        // 默认q1当值
        Queue<Pet> dutyq = queue1;
        Queue<Pet> other = queue2;
        if (!isQueue1){
            // q2当值
            dutyq = queue2;
            other = queue1;
        }
        // 先看非当值的是否Wie空
        if (other.isEmpty()){
            return dutyq.poll();
        }else {
            return other.poll();
        }
    }



    public boolean isEmpty(){
        return catSize + dogSize == Constants.NUM_0;
    }


    public boolean isCatEmpty(){
        return catSize == Constants.NUM_0;
    }

    public boolean isDogEmpty(){
        return dogSize == Constants.NUM_0;
    }

    public Queue<Pet> getQueue1() {
        return queue1;
    }

    public Queue<Pet> getQueue2() {
        return queue2;
    }

    public boolean isQueue1() {
        return isQueue1;
    }

    public int getCatSize() {
        return catSize;
    }

    public int getDogSize() {
        return dogSize;
    }

    public static void main(String[] args) {
        // 猫狗猫猫狗
        Pet[] pets = {new Cat(Constants.CAT), new Dog(Constants.DOG), new Cat(Constants.CAT), new Cat(Constants.CAT), new Dog(Constants.DOG)};
        CatDogQueue2 catDogQueue2 = new CatDogQueue2();
        // 先放三个
        for (int i = 0; i < pets.length-2; i++){
            catDogQueue2.add(pets[i]);
        }
        // 当前q
        System.out.printf("q1 : %s, q2 : %s \n", catDogQueue2.getQueue1(), catDogQueue2.getQueue2());
        // polldog
        System.out.printf("polldog：%s\n", catDogQueue2.pollDog());
        System.out.printf("q1 : %s, q2 : %s \n", catDogQueue2.getQueue1(), catDogQueue2.getQueue2());
        // pollCat
        System.out.printf("pollcat：%s\n", catDogQueue2.pollCat());
        System.out.printf("q1 : %s, q2 : %s\n", catDogQueue2.getQueue1(), catDogQueue2.getQueue2());
        // 放入剩下两个
        for (int i = 3; i < pets.length; i++){
            catDogQueue2.add(pets[i]);
        }
        System.out.printf("q1 : %s, q2 : %s\n", catDogQueue2.getQueue1(), catDogQueue2.getQueue2());
        System.out.printf("pollall：%s\n", catDogQueue2.pollAll());
        System.out.printf("q1 : %s, q2 : %s\n", catDogQueue2.getQueue1(), catDogQueue2.getQueue2());


    }

}
