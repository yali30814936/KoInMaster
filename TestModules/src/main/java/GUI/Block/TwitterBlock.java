
package GUI.Block;
import Posts.Post;
import Posts.TYPE;
import Posts.TwitterPost;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

public class TwitterBlock extends JPanel{
    private HyperLink title;
    private final BlockTextField date;
    private final BlockTextArea text;
    private final int mediaSize;
    private final List<String> mediaList;

    public TwitterBlock(Post post) throws IOException {
        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        if (post.getType() == TYPE.TWEET) {
            title = new HyperLink(String.format("%s在%s發布了推文", post.getName(), post.getPlatform().toString()), ((TwitterPost) post).getUrl());
        } else if (post.getType() == TYPE.RT) {
            title = new HyperLink(String.format("%s在%s轉推了%s的推文", post.getName(), post.getPlatform().toString(), ((TwitterPost) post).getUser()), ((TwitterPost) post).getUrl());
        } else if (post.getType() == TYPE.REPLY) {
            title = new HyperLink(String.format("%s在%s回復了%s的推文", post.getName(), post.getPlatform().toString(), ((TwitterPost) post).getUser()), ((TwitterPost) post).getUrl());
        } else if (post.getType() == TYPE.QUOTED) {
            title = new HyperLink(String.format("%s在%s引用了%s的推文", post.getName(), post.getPlatform().toString(), ((TwitterPost) post).getUser()), ((TwitterPost) post).getUrl());
        }

        Box hBox1 = Box.createHorizontalBox();
        JButton detail = new JButton("更多資訊");
        if (post.getMedia().size() <= 1) {
            detail.setEnabled(false);
        } else {
            detail.setEnabled(true);
        }

        hBox1.add(title);
        hBox1.add(Box.createHorizontalGlue());
        hBox1.add(detail);
        add(hBox1);
        detail.addActionListener(new OpenDetail());
        SimpleDateFormat ft = new SimpleDateFormat("MM月dd日 HH:mm");
        date = new BlockTextField(ft.format(post.getPublishedTime()));
        //Date.setBorder(new LineBorder(Color.red));
        Box hBox2 = Box.createHorizontalBox();
        hBox2.add(date);
        hBox2.add(Box.createHorizontalGlue());
        add(hBox2);


        text = new BlockTextArea(post.getText());
        //Text.setBorder(new LineBorder(Color.red));
        Box hBox3 = Box.createHorizontalBox();
        hBox3.add(text);
        hBox3.add(Box.createHorizontalGlue());
        add(hBox3);
        Box hBox4 = Box.createHorizontalBox();
        mediaSize = post.getMedia().size();
        mediaList = post.getMedia();
        if (post.getMedia().size() != 0) {
            add(hBox4);
            PutImageIcon put = new TwitterBlock.PutImageIcon(hBox4, post.getMedia().get(0));
            put.execute();
        }
    }
    private class OpenDetail implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HyperLink moreLink = title;
            BlockTextField moreDate = date;
            BlockTextArea moreText = text;
            JFrame moreFrame = new JFrame();
            moreFrame.setSize(1000, 1000);
            moreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            moreFrame.setVisible(true);
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            Box hBox1 = Box.createHorizontalBox();
            Box hBox2 = Box.createHorizontalBox();
            Box hBox3 = Box.createHorizontalBox();
            Box hBox4 = Box.createHorizontalBox();
            Box hBox5 = Box.createVerticalBox();
            hBox1.add(moreLink);
            hBox1.add(Box.createHorizontalGlue());
            hBox5.add(hBox1);
            hBox2.add(moreDate);
            hBox2.add(Box.createHorizontalGlue());
            hBox5.add(hBox2);
            hBox3.add(moreText);
            hBox3.add(Box.createHorizontalGlue());
            hBox5.add(hBox3);
            JPanel mediaPanel = new JPanel(new GridLayout(mediaSize, 1));
            for(String url:mediaList){
                URL ur = null;
                try {
                    ur = new URL(url);
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
                Image image = null;
                try {
                    image = ImageIO.read(ur);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                JLabel mediatmp = new JLabel(new ImageIcon(image));
                mediaPanel.add(mediatmp);
            }
            hBox4.add(mediaPanel);
            hBox5.add(hBox4);
            panel.add(hBox5);
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(20);
            scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
            moreFrame.add(scrollPane);

        }
    }
    private static class PutImageIcon extends SwingWorker<JLabel, Object> {
        private final Box parent;
        private final String url;

        public PutImageIcon(Box parent, String url) {
            this.parent = parent;
            this.url = url;
        }

        @Override
        protected JLabel doInBackground() throws Exception {
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
            parent.add(label);
            parent.revalidate();
            return null;
        }
    }
}