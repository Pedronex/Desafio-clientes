"use client";

import { useState, useEffect } from "react";
import { redirect } from "next/navigation";
import { parseCookies, destroyCookie } from "nookies";

import { Clients } from "@/components/clients";
import { Filter } from "@/components/filter";
import { Client } from "@/models/client";
import { api } from "@/services/api";
import { EditClient } from "@/components/editClient";
import Router from "next/router";

export default function Home() {
  const [clients, setClients] = useState<Client[]>([]);
  const [allClients, setAllClients] = useState<Client[]>([]);
  const [clientEdit, setClientEdit] = useState<Client | null>(null);
  const [isEditing, setIsEditing] = useState(false);

  async function getClients() {
    setAllClients([]);
    const { token } = parseCookies();

    if (typeof token === "string") {
      const { data } = await api.get("/clients", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      setClients(data);
      setAllClients(data);
    } else {
      destroyCookie(undefined, "token");
    }
  }

  async function deleteClient(idClient: string) {
    const { token } = parseCookies();

    if (typeof token === "string") {
      const { status } = await api.delete(`/client?id=${idClient}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      if (status === 204) {
        alert("Cliente deletado");
        Router.reload();
      } else {
        throw new Error("Não foi possivel deletar o cliente");
      }
    }
  }

  useEffect(() => {
    getClients();
  }, []);

  return (
    <div className="w-screen flex flex-col justify-center items-center min-h-screen">
      {isEditing && clientEdit ? (
        <EditClient
          client={clientEdit}
          success={() => {
            setAllClients([]);
            setClients([]);
            setClientEdit(null);
            getClients();
          }}
          error={alert}
          cancel={() => {
            setClientEdit(null);
            setIsEditing(false);
          }}
        />
      ) : (
        <>
          <Filter listClients={allClients} setListClients={setClients} />
          <div className="h-3" />
          <Clients
            clients={clients}
            handleDelete={deleteClient}
            handleEdit={(client) => {
              setClientEdit(client);
              setIsEditing(true);
            }}
          />
        </>
      )}
    </div>
  );
}
