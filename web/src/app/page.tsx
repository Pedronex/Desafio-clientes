"use client";

import { useContext } from "react";
import { FiUsers } from "react-icons/fi";
import { useForm } from "react-hook-form";
import { api } from "@/services/api";
import { setCookie } from "nookies";
import { useRouter } from "next/navigation";

type FormType = {
  login: string;
  password: string;
};

export default function Login() {
  const { register, handleSubmit } = useForm<FormType>();
  const navigation = useRouter();

  async function handleSignIn({ login, password }: FormType) {
    const { data } = await api.post<{ message?: string; token?: string }>(
      "/login",
      {
        login,
        password,
      }
    );

    if (data.token && !data.message) {
      setCookie(undefined, "token", data.token);
      navigation.push("/home");
    } else {
      alert("Alerta: " + data.message);
    }
  }

  return (
    <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
      <div className="sm:mx-auto sm:w-full sm:max-w-sm flex items-center flex-col">
        <FiUsers size={50} />
        <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight">
          Faça login com sua conta
        </h2>
      </div>

      <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm space-y-6">
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

        <p className="mt-10 text-center text-sm text-white-500">
          Deseja se registrar?
          <a
            href="#"
            className="font-semibold leading-6 bg-[#2773b4] p-1 rounded ml-1 text-white hover:text-blue-500"
          >
            Clique aqui
          </a>
        </p>
      </div>
    </div>
  );
}
