package rbrtree_assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class RBTree_Assignment {

    private final int RED_COLOR = 0;
    private final int BLACK_COLOR = 1;
    int colorc = 0;
    int recolorc = 0;
    int rdouble= 0;
    int search = 0;
    int count=0;
    private class RBNode {

        int key = -1, color = BLACK_COLOR;
        RBNode left = nullNode;
        RBNode right = nullNode, parent = nullNode;

        RBNode(int key) {
            this.key = key;
        }
    }

    private final RBNode nullNode = new RBNode(-1);
    private RBNode root = nullNode;

    public ArrayList<Integer> printTree(RBNode node,ArrayList<Integer> tree) {
        if (node == nullNode) {
            return tree;
        }
        tree=printTree(node.left,tree);
        System.out.print(" "+node.key);
        tree.add(node.key);
        printTree(node.right,tree);

        return tree;
    }
    private RBNode searchNode(RBNode findNode, RBNode node) {
        
        search+=1;
        if (root == nullNode) {
            return null;
        }
        if (findNode.key < node.key) {
            if (node.left != nullNode) {
                return searchNode(findNode, node.left);
            }
        } else if (findNode.key > node.key) {
            if (node.right != nullNode) {
                return searchNode(findNode, node.right);
            }
        } else if (findNode.key == node.key) {
            return node;
        }
        return null;
    }

    private void insertNode(RBNode node) {
        RBNode temp = root;
        if (root == nullNode) {
            root = node;
            node.color = BLACK_COLOR;
            node.parent = nullNode;
        } else {
            node.color = RED_COLOR;
            while (true) {
                if (node.key < temp.key) {
                    if (temp.left == nullNode) {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else if (node.key >= temp.key) {
                    if (temp.right == nullNode) {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            fixTree(node);
        }
    }

    private void fixTree(RBNode node) {
        int flag=0;
        while (node.parent.color == RED_COLOR) {
            RBNode uncle = nullNode;
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right;

                if (uncle != nullNode && uncle.color == RED_COLOR) {
                
                    node.parent.color = BLACK_COLOR;
                    uncle.color = BLACK_COLOR;
                    node.parent.parent.color = RED_COLOR;
                    colorc+=3;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.right) {
                    
                    node = node.parent;
                    recolorc+=1;
                    flag=1;
                    rotateLeft(node);
                }
                
                node.parent.color = BLACK_COLOR;
                node.parent.parent.color = RED_COLOR;
                colorc+=2;
                recolorc+=1;
                if(flag == 1)
                {
                    rdouble+=1;
                    flag=0;
                }
                rotateRight(node.parent.parent);
            } else {
                uncle = node.parent.parent.left;
                 if (uncle != nullNode && uncle.color == RED_COLOR) {
                    
                    node.parent.color = BLACK_COLOR;
                    uncle.color = BLACK_COLOR;
                    node.parent.parent.color = RED_COLOR;
                    colorc+=3;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.left) {
                   
                    node = node.parent;
                    recolorc+=1;
                    flag=1;
                    rotateRight(node);
                }
               
                node.parent.color = BLACK_COLOR;
                node.parent.parent.color = RED_COLOR;
                colorc+=2;
                recolorc+=1;
                if(flag == 1)
                {
                    rdouble+=1;
                    flag=0;
                }
                rotateLeft(node.parent.parent);
            }
        }
        colorc+=1;
        root.color = BLACK_COLOR;
    }

    void rotateLeft(RBNode node) {
        if (node.parent != nullNode) {
            if (node == node.parent.left) {
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != nullNode) {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        } else {
            RBNode right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = nullNode;
            root = right;
        }
    }

    void rotateRight(RBNode node) {
        if (node.parent != nullNode) {
            if (node == node.parent.left) {
                node.parent.left = node.left;
            } else {
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != nullNode) {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        } else {
            RBNode left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = nullNode;
            root = left;
        }
    }

    public void display() {
        Scanner scan = new Scanner(System.in);
        int choice = 1;
        int item,item1;
        RBNode node;
        ArrayList<Integer> tree1=new ArrayList<>();
        ArrayList<Integer> tree2=new ArrayList<>();
        ArrayList<Integer> tree3=new ArrayList<>();
        Set<Integer> treefinal=new TreeSet<>();
            System.out.println("\t\tSearching in Red Black Tree ");
            System.out.println("****************************************************");
            System.out.println("Insert into Tree1");
            System.out.println("Enter element to insert(insert -9999 to terminate)");
            item = scan.nextInt();
            while (item != -9999) {
                node = new RBNode(item);
                insertNode(node);
                item = scan.nextInt();
                }
            System.out.println("Inorder of Tree1");
            tree1=printTree(root,tree1);
            item =0;
            root = nullNode;
            System.out.println("\nInsert into Tree2");
            System.out.println("Enter element to insert(insert -9999 to terminate)");
            item = scan.nextInt();
            while (item != -9999) {
                node = new RBNode(item);
                insertNode(node);
                item = scan.nextInt();
                }
            System.out.println("Inorder of Tree2");
            tree2=printTree(root,tree2);
            tree3=tree1;
            do{
            System.out.println("\nChoose from Following");
            System.out.println("\n1)Without Using Set datastructure\t2)With Using Set datastructure\t3)Exit");    
            choice=scan.nextInt();
            switch (choice) {
                case 1:
                        System.out.println("Merging");
                        for(int i=0;i<tree2.size();i++)
                        {
                            int temp=tree2.get(i);
                            tree3.add(temp);
                        }
                        Collections.sort(tree3);
                        for(int i=0;i<tree3.size();i++)
                        {
                            int it=tree3.get(i);
                            node = new RBNode(it);
                            insertNode(node);
                        }
                        System.out.println("Enter Element to search:");
                        item= scan.nextInt();
                        node = new RBNode(item);
                        long start=System.nanoTime();
                        if((searchNode(node, root) != null))
                        {
                            System.out.println(item+" Found");
                            
                            long end=System.nanoTime();
                            System.out.println("Time spend to Search "+(end-start)+" nanoseconds");
                        }
                        else
                            System.out.println(item+" Not Found");
                        break;

                case 2:
                        System.out.println("Merging");
                        toSetConversion tsc=new toSetConversion(tree1,tree2);
                        treefinal =tsc.union();
                        Iterator<Integer> inti=treefinal.iterator();
                        while(inti.hasNext())
                        {
                            item1=inti.next();
                            
                            node = new RBNode(item1);
                            insertNode(node);
                        }
                                
                        System.out.println("Enter Element to search:");
                        item= scan.nextInt();
                        node = new RBNode(item);
                        long start1=System.nanoTime();
                        
                        if((searchNode(node, root) != null))
                        {
                            System.out.println(item+" Found");
                            
                            long end=System.nanoTime();
                            System.out.println("Time spend to Search "+(end-start1)+" nanoseconds");
                        }
                        else
                            System.out.println(item+" Not Found");
                        break;
                case 3:
                        System.exit(0);
                    break;
            }
            }while(choice!=3);
    
    }
    
    public static void main(String[] args) {
        RBTree_Assignment rb=new RBTree_Assignment();
        rb.display();
    }
}

class toSetConversion{
    Set<Integer> t1set;
    Set<Integer> t2set;
    toSetConversion(ArrayList<Integer> tree1,ArrayList<Integer> tree2)
    {
        t1set=new TreeSet<>(tree1);
        t2set=new TreeSet<>(tree2);
    }
    Set union()
    {
        t1set.addAll(t2set);
        return t1set;
    }
}