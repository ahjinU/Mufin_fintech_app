interface HeaderProps {
  children?: React.ReactElement;
}

export default function Header({ children, ...props }: HeaderProps) {
  return (
    <div className="h-[5.6rem] w-full flex items-center pl-[1.2rem]">
      {children}
    </div>
  );
}
