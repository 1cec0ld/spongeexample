package com.gmail.ak1cec0ld.plugins.spongeexample.storage;

import com.gmail.ak1cec0ld.plugins.spongeexample.SpongeExample;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.DataManipulator;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.manipulator.ImmutableDataManipulator;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableSingleData;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractSingleData;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.persistence.AbstractDataBuilder;
import org.spongepowered.api.data.persistence.InvalidDataException;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.data.value.mutable.Value;

import java.util.Optional;

public class UsageData extends AbstractSingleData<String, UsageData, UsageData.Immutable>
        implements DataManipulator<UsageData, UsageData.Immutable> {

    public UsageData(String item) {
        super(SpongeExample.KEY, item);
    }

    @Override
    public Optional<UsageData> fill(DataHolder dataHolder, MergeFunction overlap) {
        dataHolder.get(UsageData.class)
                .ifPresent(data -> this.setValue(overlap.merge(this, data).getValue()));
        return Optional.of(this);
    }

    @Override
    public Optional<UsageData> from(DataContainer container) {
        container.getString(SpongeExample.KEY.getQuery()).ifPresent(this::setValue);
        return Optional.of(this);
    }

    @Override
    public UsageData copy() {
        return new UsageData(this.getValue());
    }

    @Override
    public Immutable asImmutable() {
        return new Immutable(this.getValue());
    }

    @Override
    public int getContentVersion() {
        return 2;
    }

    @Override
    protected Value<String> getValueGetter() {
        return Sponge.getRegistry().getValueFactory().createValue(SpongeExample.KEY, getValue());
    }

    @Override
    public DataContainer toContainer() {
        return super.toContainer().set(SpongeExample.KEY.getQuery(), this.getValue());
    }

    public static class Immutable
            extends AbstractImmutableSingleData<String, Immutable, UsageData>
            implements ImmutableDataManipulator<Immutable, UsageData> {

        public Immutable(String item) {
            super(SpongeExample.KEY, item);
        }

        @Override
        public UsageData asMutable() {
            return new UsageData(this.getValue());
        }

        @Override
        public int getContentVersion() {
            return 2;
        }

        @Override
        protected ImmutableValue<String> getValueGetter() {
            return Sponge.getRegistry().getValueFactory().createValue(
                    SpongeExample.KEY, getValue()).asImmutable();
        }

        @Override
        public DataContainer toContainer() {
            return super.toContainer().set(SpongeExample.KEY.getQuery(), this.getValue());
        }

    }

    public static class Builder extends AbstractDataBuilder<UsageData>
            implements DataManipulatorBuilder<UsageData, Immutable> {

        public Builder() {
            super(UsageData.class, 2);
        }

        @Override
        public UsageData create() {
            return new UsageData("");
        }

        @Override
        public Optional<UsageData> createFrom(DataHolder dataHolder) {
            return Optional.of(dataHolder.get(UsageData.class).orElse(create()));
        }

        @Override
        protected Optional<UsageData> buildContent(DataView container) throws InvalidDataException {
            return Optional.empty();
        }
    }

}