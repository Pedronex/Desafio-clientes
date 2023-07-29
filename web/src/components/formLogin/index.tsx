'use client'

import { useForm } from "react-hook-form";

type FormType = {
    login: string;
    password: string;
};

interface FormLoginProp {
    handleSignIn: (data: FormType) => Promise<void>;
}

export function FormLogin({handleSignIn}: FormLoginProp) {

    const { register, handleSubmit } = useForm<FormType>();

    return (
        <form onSubmit={handleSubmit(handleSignIn)}>
          <div>
            <label
              htmlFor="name"
              className="block text-sm font-medium leading-6"
            >
              Usuario
            </label>
            <div className="my-2">
              <input
                {...register("login")}
                id="login"
                name="login"
                type="text"
                required
                className="block w-full rounded-md border-0 py-1.5 shadow-sm ring-1 ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
              />
            </div>
          </div>

          <div>
            <div className="flex items-center justify-between">
              <label
                htmlFor="password"
                className="block text-sm font-medium leading-6"
              >
                Senha
              </label>
            </div>
            <div className="my-2">
              <input
                {...register("password")}
                id="password"
                name="password"
                type="password"
                autoComplete="current-password"
                required
                className="block w-full rounded-md border-0 py-1.5 shadow-sm ring-1 ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
              />
            </div>
          </div>

          <div>
            <button className="flex w-full justify-center rounded-md bg-blue-900 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-blue-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-blue-600 ">
              Login
            </button>
          </div>
        </form>
    )
}