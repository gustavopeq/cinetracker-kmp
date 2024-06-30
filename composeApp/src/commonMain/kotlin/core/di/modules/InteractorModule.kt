package core.di.modules

import features.browse.domain.BrowseInteractor
import features.details.domain.DetailsInteractor
import features.home.ui.domain.HomeInteractor
import org.koin.dsl.module

val interactorModule = module {
    single<BrowseInteractor> { BrowseInteractor(get(), get()) }
    single<DetailsInteractor> { DetailsInteractor(get(), get(), get()) }
    single { HomeInteractor(get()) }
}