<#import "layout.ftl" as page>

<@page.layout "Profile | Clean Architecture Analyzer">

    <form action="/analyze">
        <div class="mb-3 mt-3">
            <h3>To start code analyzing, enter the form below:</h3>
        </div>

        <div class="form-group">
            <label>
                <input class="form-control"  name="owner" type="text" placeholder="Owner nickname"
                       autofocus="autofocus" required/>
            </label>
        </div>

        <div class="form-group">
            <label>
                <input class="form-control"  name="repo" type="text" placeholder="Repository name" required/>
            </label>
        </div>

        <div class="form-group">
            <label>
                <input class="form-control"  name="sourceDir" type="text"
                       placeholder="Source files' directory (preferably .../main)" required/>
            </label>
        </div>

        <button class="btn btn-primary mb-3" type="submit">Analyze</button>

        <div class="mb-3 mt-3">
            <h6>Last review</h6>
            <#if review??>
                <#assign problems = review.getProblems()>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="row">${review.getDate()}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list problems as problem>
                        <tr class="${(problem.getType().name() == "ERROR")?then('table-danger',
                        (problem.getType().name() == "SUCCESS")?then('table-success', "table-warning"))}">
                            <th scope="row">${problem.getType().name()}</th>
                            <td>${problem.toString()}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </#if>
        </div>
    </form>

</@page.layout>