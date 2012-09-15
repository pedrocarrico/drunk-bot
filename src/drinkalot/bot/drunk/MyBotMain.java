package drinkalot.bot.drunk;

import java.io.FileInputStream;
import java.util.Properties;

public class MyBotMain {
    
    public static void main(String[] args) throws Exception {
        
        Properties p = new Properties();
        p.load(new FileInputStream("irc.properties"));
        
        // Now start our bot up.
        DrunkBot bot = new DrunkBot(p.getProperty("irc.user.name"), p.getProperty("irc.bot.master"));
        
        // Enable debugging output.
        bot.setVerbose(p.getProperty("irc.bot.verbose").equals("true"));
        
        // Connect to the IRC server.
        bot.connect(p.getProperty("irc.server.location"),
                    Integer.parseInt(p.getProperty("irc.server.port")),
                    p.getProperty("irc.server.password"));
        
        String botPassword = p.getProperty("irc.user.password");
        if (botPassword != null)
            bot.identify(botPassword);
        
        String[] channels = p.getProperty("irc.server.channels").split(",");
        
        for (String channel : channels) {
            bot.joinChannel(channel);
        }
    }
}
