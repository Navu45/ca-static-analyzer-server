<#import "layout.ftl" as page/>
<#import 'spring.ftl' as spring>
<@page.layout "Sign up | Clean Architecture Analyzer">
    <form method="post" action="/registration">
        <h2>Create your account</h2>

        <div class="form-group">
            <label>
                <input type="text"
                       class="form-control ${(usernameError?? || userExistsError??)?string('is-invalid', '')}"
                       value="<#if userForm?? && userForm.getUsername()??>${userForm.getUsername()}</#if>"
                       placeholder="Email"
                       name="username"/>
            </label>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
            <#if userExistsError??>
                <div class="invalid-feedback">
                    ${userExistsError}
                </div>
            </#if>
        </div>

        <div class="form-group">
            <label>
                <input type="password"
                       class="form-control ${(passwordError??)?string('is-invalid', ' ')}"
                       name="password"  placeholder="Password"/>
            </label>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>

        <div class="form-group">
            <label>
                <input type="password"
                       class="form-control ${(confirmationError??)?string('is-invalid', '')}"
                       name="confirmPassword"  placeholder="Confirm the password"/>
            </label>
            <#if confirmationError??>
                <div class="invalid-feedback">
                    ${confirmationError}
                </div>
            </#if>
            <#if confirmPasswordError??>
                <div class="invalid-feedback">
                    ${confirmPasswordError}
                </div>
            </#if>
        </div>

        <button class="btn btn-primary mb-3" type="submit">Submit</button>
    </form>
</@page.layout>