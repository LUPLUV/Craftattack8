package io.papermc.paper.command.subcommands;

import com.destroystokyo.paper.io.SyncLoadFinder;
import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import io.papermc.paper.command.CommandUtil;
import io.papermc.paper.command.PaperSubcommand;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.GRAY;
import static net.kyori.adventure.text.format.NamedTextColor.GREEN;
import static net.kyori.adventure.text.format.NamedTextColor.RED;

@DefaultQualifier(NonNull.class)
public final class SyncLoadInfoCommand implements PaperSubcommand {
    @Override
    public boolean execute(final CommandSender sender, final String subCommand, final String[] args) {
        this.doSyncLoadInfo(sender, args);
        return true;
    }

    @Override
    public List<String> tabComplete(final CommandSender sender, final String subCommand, final String[] args) {
        return CommandUtil.getListMatchingLast(sender, args, "clear");
    }

    private void doSyncLoadInfo(final CommandSender sender, final String[] args) {
        if (!SyncLoadFinder.ENABLED) {
            sender.sendMessage(text("This command requires the server startup flag '-Dpaper.debug-sync-loads=true' to be set.", RED));
            return;
        }

        if (args.length > 0 && args[0].equals("clear")) {
            SyncLoadFinder.clear();
            sender.sendMessage(text("Sync load data cleared.", GRAY));
            return;
        }

        File file = new File(new File(new File("."), "debug"),
            "sync-load-info" + DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss").format(LocalDateTime.now()) + ".txt");
        file.getParentFile().mkdirs();
        sender.sendMessage(text("Writing sync load info to " + file, GREEN));


        try {
            final JsonObject data = SyncLoadFinder.serialize();

            StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            jsonWriter.setIndent(" ");
            jsonWriter.setLenient(false);
            Streams.write(data, jsonWriter);

            String fileData = stringWriter.toString();

            try (
                PrintStream out = new PrintStream(new FileOutputStream(file), false, "UTF-8")
            ) {
                out.print(fileData);
            }
            sender.sendMessage(text("Successfully written sync load information!", GREEN));
        } catch (Throwable thr) {
            sender.sendMessage(text("Failed to write sync load information!", RED));
            thr.printStackTrace();
        }
    }
}
