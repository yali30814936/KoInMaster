package KoInMaster.TestModules.GUI;

import KoInMaster.TestModules.Posts.Post;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;

public class Block extends JPanel {
	private JLabel Title;
	private JLabel Date;
	private JLabel Text;
	private JLabel Mediatmp;
	private JPanel MediaPanel;
	public Block(Post post) throws IOException {
		super(new GridBagLayout());
		GridBagConstraints gridBagConstraints=new GridBagConstraints();
		gridBagConstraints.weightx=1;
		gridBagConstraints.weighty=1;
		gridBagConstraints.fill=GridBagConstraints.HORIZONTAL;
		Title=new JLabel( String.format("%s在%s平台發布了%s類型的貼文",post.getName(),post.getPlatform().toString(),post.getType()));
		gridBagConstraints.gridx=0;
		gridBagConstraints.gridy=0;
		gridBagConstraints.gridwidth=3;
		add(Title,gridBagConstraints);
		gridBagConstraints.gridx=0;
		gridBagConstraints.gridy=1;
		gridBagConstraints.weightx=3;
		SimpleDateFormat ft = new SimpleDateFormat("MM月dd日 HH:mm");
		Date=new JLabel(String.format(ft.format(post.getPublishedTime())));
		add(Date,gridBagConstraints);
		gridBagConstraints.gridx=0;
		gridBagConstraints.gridy=2;
		gridBagConstraints.gridwidth=3;
		gridBagConstraints.gridheight=2;
		Text = new JLabel(post.getText());
		add(Text,gridBagConstraints);
		gridBagConstraints.gridx=0;
		gridBagConstraints.gridy=4;
		gridBagConstraints.gridwidth=2;
		gridBagConstraints.gridheight=1;

		MediaPanel = new JPanel(new GridLayout(1,3));
		for(String url:post.getMedia()){
			URL ur = new URL(url);
			Image image = ImageIO.read(ur);
			//image=image.getScaledInstance(400 ,600,Image.SCALE_REPLICATE);
			Mediatmp = new JLabel(new ImageIcon(image));
			MediaPanel.add(Mediatmp);
		}
		add(MediaPanel,gridBagConstraints);
	}
}
