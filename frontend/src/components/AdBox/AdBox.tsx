import Link from 'next/link';
import Image from 'next/image';

interface AdBoxProps {
  mode: 'INTERACTIVE' | 'STATIC' | 'WEATHER';
  subText: string;
  title: string;
  icon?: string;
  link?: string;
}

export default function AdBox({
  mode,
  subText,
  title,
  icon,
  link,
}: AdBoxProps) {
  const iconContent = icon ? (
    <Image
      src={icon}
      width={42}
      height={42}
      alt={title}
      className="w-[4.2rem] h-[4.2rem]"
    />
  ) : null;

  const adContent = (
    <div
      className={`w-full h-[8.4rem] px-[1rem] rounded-[2rem] flex justify-start items-center ${
        mode === 'WEATHER' ? 'bg-custom-light-gray' : 'bg-custom-purple'
      } `}
    >
      {iconContent}
      <div
        className={`ml-[0.8rem] ${
          mode === 'WEATHER' ? 'text-custom-black' : 'text-custom-white'
        } `}
      >
        <div className="custom-light-text">{subText}</div>
        <div className="custom-semibold-text">{title}</div>
      </div>
    </div>
  );

  return mode === 'INTERACTIVE' && link ? (
    <Link href={link}>{adContent}</Link>
  ) : (
    adContent
  );
}
