export default function CompanyLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return <section className="flex flex-col gap-[1rem]">{children}</section>;
}
