public class DeletedPageStack {

    private Node<Page> top;
    private int size;

    public DeletedPageStack(){
        top = null;
        size = 0;
    }

    public void push(Page page){
        Node<Page> newNode = new Node<>(page);

        newNode.next = top;
        top = newNode;
        size++;
    }

    public Page pop(){
        if (top == null){
            return null;
        }
        

        Page removedPage = top.data;
        top = top.next;
        size--;
        return removedPage;
    }

    public Page peek(){
        if (top==null){
            return null;
        }

        return top.data;
    }

    public boolean isEmpty(){
        return top == null;
    }

    public int size(){
        return size;
    }

    public void clear(){
        top =null;
        size = 0;
    }
}
