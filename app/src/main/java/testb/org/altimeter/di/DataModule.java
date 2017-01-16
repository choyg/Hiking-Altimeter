package testb.org.altimeter.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import testb.org.altimeter.data.AltitudeRepository;
import testb.org.altimeter.data.AltitudeRepositoryImpl;

@Module(includes = AppModule.class)
public class DataModule {
    @Provides
    @Singleton
    AltitudeRepository provideAltitudeRepository(Application application) {
        return new AltitudeRepositoryImpl(application);
    }
}