package indooptik.main;


import javax.swing.JFrame;
 
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TestTree extends JFrame
{
    private JTree tree;
    private JLabel selectedLabel;
     
    public TestTree()
    {
        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        //create the child nodes
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        vegetableNode.add(new DefaultMutableTreeNode("Capsicum"));
        vegetableNode.add(new DefaultMutableTreeNode("Carrot"));
        vegetableNode.add(new DefaultMutableTreeNode("Tomato"));
        vegetableNode.add(new DefaultMutableTreeNode("Potato"));
         
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
        ModelExampleTree modelExampleTree = new ModelExampleTree();
        modelExampleTree.setId(1);
        modelExampleTree.setName("nama nya");
        modelExampleTree.setContent("contennya");
        fruitNode.add(new DefaultMutableTreeNode(modelExampleTree));
        fruitNode.add(new DefaultMutableTreeNode("Mango"));
        fruitNode.add(new DefaultMutableTreeNode("Apple"));
        fruitNode.add(new DefaultMutableTreeNode("Grapes"));
        fruitNode.add(new DefaultMutableTreeNode("Orange"));
 
        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);
         
        //create the tree by passing in the root node
        tree = new JTree(root);
 
        /*ImageIcon imageIcon = new ImageIcon(TreeExample.class.getResource("/leaf.jpg"));
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();       
        renderer.setLeafIcon(imageIcon);*/
         
        //tree.setCellRenderer(renderer);
        tree.setShowsRootHandles(true);
        tree.setRootVisible(false);
        add(new JScrollPane(tree));
         
        selectedLabel = new JLabel();
        add(selectedLabel, BorderLayout.SOUTH);
 
        tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                
                if(selectedNode.getUserObject() instanceof ModelExampleTree) {
                    String str = ((ModelExampleTree)selectedNode.getUserObject()).getContent();
                    selectedLabel.setText(str);
                }else{
                    selectedLabel.setText(selectedNode.getUserObject().toString());
                }
            }
        });
         
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JTree Example");       
        this.setSize(200, 200);
        this.setVisible(true);
    }
     
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestTree();
            }
        });
    }      
}