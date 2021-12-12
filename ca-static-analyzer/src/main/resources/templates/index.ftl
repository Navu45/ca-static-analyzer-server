<#import "layout.ftl" as page>

<@page.layout "Home page | Clean Architecture Analyzer">
    <td class="bs-docs-section">
        <div class="jumbotron">
            <h1 class="display-3">Welcome to Clean Architecture Analyzer</h1>
            <p class="lead">This is a simple static analyzer that explore your GitHub
                repository Java project on Clean Architecture pattern.</p>
            <hr class="my-4">
            <p>First, add a few JAVADOC comments with these annotations to each <i>.java</i>
                file so that the parser can get what Clean Architecture level this class belongs to.</p>
            <p class="lead">
                <a class="btn btn-primary btn-lg" href="/profile" role="button">Try it out</a>
            </p>
        </div>


        <div class="card-deck mb-2">
            <div class="card border-secondary mb-3 " style="max-width: 20rem;">
                <div class="card-header">Enterprise Domain Rules</div>
                <div class="card-body">
                    <h4 class="card-title">@DomainEntity</h4>
                    <p class="card-text">Business logic that is common to many applications.</p>
                </div>
            </div>
            <div class="card border-secondary mb-3 " style="max-width: 20rem;">
                <div class="card-header">Application Business Rules</div>
                <div class="card-body">
                    <h4 class="card-title">@UseCase</h4>
                    <p class="card-text">Application logic.</p>
                </div>
            </div>
            <div class="card border-secondary mb-3 " style="max-width: 20rem;">
                <div class="card-header">Interface Adapters</div>
                <div class="card-body">
                    <h4 class="card-title">@InterfaceAdapter</h4>
                    <p class="card-text">Adapters between Use Cases and the outside world. This includes Presenters
                        from MVPs, as well as Gateways (the more popular name for the repository).</p>
                </div>
            </div>
            <div class="card border-secondary mb-3 " style="max-width: 20rem;">
                <div class="card-header">Frameworks & Drivers</div>
                <div class="card-body">
                    <h4 class="card-title">@Framework</h4>
                    <p class="card-text">The outermost layer, everything else lies here:
                        UI, database, http client, etc.</p>
                </div>
            </div>
        </div>
</@page.layout>