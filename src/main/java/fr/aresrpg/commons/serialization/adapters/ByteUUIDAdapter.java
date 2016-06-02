package fr.aresrpg.commons.serialization.adapters;

import fr.aresrpg.commons.reflection.ParametrizedClass;

import java.util.UUID;

public class ByteUUIDAdapter implements Adapter<UUID, byte[]>{
	public static final Adapter<UUID , byte[]> INSTANCE = new ByteUUIDAdapter();
	public static final ParametrizedClass<UUID> IN = new ParametrizedClass<UUID>(){};
	public static final ParametrizedClass<byte[]> OUT = new ParametrizedClass<byte[]>(){};

	private ByteUUIDAdapter(){}

	@Override
	public byte[] adaptTo(UUID in) {
		return new byte[]{
				(byte) (in.getLeastSignificantBits() & 0xFF),
				(byte) (in.getLeastSignificantBits() << 8 & 0xFF),
				(byte) (in.getLeastSignificantBits() << 16 & 0xFF),
				(byte) (in.getLeastSignificantBits() << 24 & 0xFF),
				(byte) (in.getLeastSignificantBits() << 32 & 0xFF),
				(byte) (in.getLeastSignificantBits() << 40 & 0xFF),
				(byte) (in.getLeastSignificantBits() << 48 & 0xFF),
				(byte) (in.getLeastSignificantBits() << 56 & 0xFF),

				(byte) (in.getMostSignificantBits() & 0xFF),
				(byte) (in.getMostSignificantBits() << 8 & 0xFF),
				(byte) (in.getMostSignificantBits() << 16 & 0xFF),
				(byte) (in.getMostSignificantBits() << 24 & 0xFF),
				(byte) (in.getMostSignificantBits() << 32 & 0xFF),
				(byte) (in.getMostSignificantBits() << 40 & 0xFF),
				(byte) (in.getMostSignificantBits() << 48 & 0xFF),
				(byte) (in.getMostSignificantBits() << 56 & 0xFF)
		};
	}

	@Override
	public UUID adaptFrom(byte[] out) {
		return new UUID(getLong(out , 8), getLong(out , 0));
	}

	public long getLong(byte[] b , int offset){
		return ((((long) b[7+offset]) << 56) | (((long) b[6+offset] & 0xff) << 48) | (((long) b[5+offset] & 0xff) << 40)
				| (((long) b[4+offset] & 0xff) << 32) | (((long) b[3+offset] & 0xff) << 24) | (((long) b[2+offset] & 0xff) << 16)
				| (((long) b[1+offset] & 0xff) << 8) | (((long) b[offset] & 0xff)));
	}

	@Override
	public ParametrizedClass<UUID> getInType() {
		return IN;
	}

	@Override
	public ParametrizedClass<byte[]> getOutType() {
		return OUT;
	}
}
