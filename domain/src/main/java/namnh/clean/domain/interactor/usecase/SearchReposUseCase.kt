package namnh.clean.domain.interactor.usecase

import io.reactivex.Maybe
import namnh.clean.domain.entity.Repo
import namnh.clean.domain.executor.PostExecutionThread
import namnh.clean.domain.executor.ThreadExecutor
import namnh.clean.domain.interactor.inputport.MaybeUseCase
import namnh.clean.domain.repository.RepoRepository

class SearchReposUseCase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val repoRepository: RepoRepository
) : MaybeUseCase<SearchReposUseCase.Input, List<Repo>>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(input: Input): Maybe<List<Repo>> {
        return repoRepository.search(input.query, input.page)
    }

    class Input(val query: String, val page: Int)
}