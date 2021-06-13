package KoInMaster.TestModules.GUI;

import KoInMaster.TestModules.Posts.Post;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Block extends JPanel {
	private JLabel Title;
	private JLabel Date;
	private JLabel Text;
	private JLabel Mediatmp;
	private JPanel MediaPanel;
	public Block(Post post) throws IOException {
		super(new GridLayout(4,1));
		Title=new JLabel( String.format("%s在%s平台發布了%s類型的貼文",post.getName(),post.getPlatform().toString(),post.getType()), (int) LEFT_ALIGNMENT);
		add(Title,0);
		SimpleDateFormat ft = new SimpleDateFormat("MM月dd日 HH:mm");
		Date=new JLabel(String.format(ft.format(post.getPublishedTime())),(int) LEFT_ALIGNMENT);
		add(Date,1);
		Text = new JLabel(post.getText());
		add(Text,2);
		MediaPanel = new JPanel(new GridLayout());
		for(String url:post.getMedia()){
			URL ur = new URL(url);
			Image image = ImageIO.read(ur);
			Mediatmp = new JLabel(new ImageIcon(image));
			MediaPanel.add(Mediatmp);
		}
		add(MediaPanel,3);
	}
}
