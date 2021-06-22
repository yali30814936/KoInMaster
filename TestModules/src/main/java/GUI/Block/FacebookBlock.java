package GUI.Block;

import Posts.FbPost;
import Posts.Post;
import Posts.TYPE;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

public class FacebookBlock extends JPanel {
    private JButton Detail;
    private FbPost fp;
    private PutImageIcon put;
    public FacebookBlock(Post post) {

        this.fp=(FbPost)post;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.white);

        Box vBox = Box.createVerticalBox();

        Box hBox = Box.createHorizontalBox();
        String topTitle;
        if(post.getType()== TYPE.POST) {
             topTitle = post.getName() + " 在 Facebook　發布了一則貼文";
        }
        else{
             topTitle = post.getName() + " 在 Facebook　分享了一則貼文";
        }
        Detail = new JButton("更多資訊");
        Detail.setEnabled(post.getMedia().size() > 1);

        hBox.add(new HyperLink(topTitle, post.getUrl()));
        hBox.add(Box.createHorizontalGlue()); // left align
        hBox.add(Detail);
        vBox.add(hBox);
        Detail.addActionListener(new FacebookBlock.OpenDetail());

        hBox = Box.createHorizontalBox();
        SimpleDateFormat ft = new SimpleDateFormat("MM月dd日 HH:mm");
        hBox.add(new BlockTextField(ft.format(post.getPublishedTime())));
        hBox.add(Box.createHorizontalGlue()); // left align
        vBox.add(hBox);

        BlockTextArea text = new BlockTextArea(post.getText());
        hBox = Box.createHorizontalBox();
        hBox.add(text);
        hBox.add(Box.createHorizontalGlue());
        vBox.add(hBox);

        add(vBox);
        if (post.getMedia().size() != 0) {
            put = new PutImageIcon(vBox, fp.getMedia().get(0));
            put.execute();
        }
    }

    public SwingWorker<Object, Object> getWorker() {
        return put;
    }

    private class OpenDetail implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame moreFrame = new JFrame();
            moreFrame.setSize(1000, 1000);
            moreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            moreFrame.setVisible(true);
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
            Box hBox1 = Box.createHorizontalBox();
            Box hBox2 = Box.createHorizontalBox();
            Box hBox3 = Box.createHorizontalBox();
            Box hBox4 = Box.createHorizontalBox();
            Box vBox = Box.createVerticalBox();
            String topTitle;
            if(fp.getType()== TYPE.POST) {
                topTitle = fp.getName() + " 在 Facebook　發布了一則貼文";
            }
            else{
                topTitle = fp.getName() + " 在 Facebook　分享了一則貼文";
            }
            hBox1.add(new HyperLink(topTitle, fp.getUrl()));
            hBox1.add(Box.createHorizontalGlue());
            vBox.add(hBox1);
            SimpleDateFormat ft = new SimpleDateFormat("MM月dd日 HH:mm");
            hBox2.add(new BlockTextField(ft.format(fp.getPublishedTime())));
            hBox2.add(Box.createHorizontalGlue());
            vBox.add(hBox2);
            JLabel text = new JLabel(fp.getText(), 2);
            hBox3.add(text);
            hBox3.add(Box.createHorizontalGlue());
            vBox.add(hBox3);
            List<String> mediaList = fp.getMedia();
            for(String url:mediaList){
                PutImageIcon put = new PutImageIcon(hBox4, url);
                put.execute();
            }
            vBox.add(hBox4);
            panel.add(vBox);
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(20);
            scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
            moreFrame.add(scrollPane);

        }
    }


    private static class PutImageIcon extends SwingWorker<Object, Object> {
        private final Box parent;
        private final String url;

        public PutImageIcon(Box parent, String url) {
            this.parent = parent;
            this.url = url;
        }

        @Override
        protected Object doInBackground() throws Exception {
            URL u;
            try {
                u = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
            Image image = ImageIO.read(u);

            int x = image.getWidth(null);
            int y = image.getHeight(null);

            if (y > 700) {
                float scale = 700f / y;
                image = image.getScaledInstance(Math.round(x * scale), Math.round(y * scale), Image.SCALE_SMOOTH);
            }

            JLabel label = new JLabel(new ImageIcon(image));
            Box box = Box.createHorizontalBox();
            box.add(label);
            parent.add(box);
//            parent.repaint();
//            parent.revalidate();
            return null;
        }
    }
}
