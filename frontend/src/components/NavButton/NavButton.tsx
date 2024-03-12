import Image from 'next/image';

interface NavButtonProps {
  mode: 'HIGHLIGHT' | 'GENERAL';
  label: string;
  link: string;
  onClick?: () => void;
}

export default function NavButton({ mode, label, ...props }: NavButtonProps) {
  const textStyle =
    mode === 'HIGHLIGHT' ? 'custom-bold-text' : 'custom-medium-text';

  return (
    <button
      className={`w-full h-[6.4rem] rounded-[2rem] px-[2rem] text-custom-black bg-custom-light-gray flex justify-between items-center ${textStyle}`}
      {...props}
    >
      {label}
      <Image
        src={'http://localhost:3000/images/icon-right.png'}
        width={16}
        height={16}
        alt="오른쪽 이동"
      />
    </button>
  );
}
