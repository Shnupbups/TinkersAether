package shnupbups.tinkersaether.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageExtendedAttack implements IMessage {

    private int entity;

    public MessageExtendedAttack() {

    }

    public MessageExtendedAttack(int entity) {
        this.entity=entity;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        entity = ByteBufUtils.readVarInt(buf, 4);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf, entity, 4);
    }

    public int getEntityId() {
        return entity;
    }
}
