interface FlexBoxProps {
  isDivided: boolean;
  topChildren?: React.ReactElement;
  bottomChildren?: React.ReactElement;
  date?: string | undefined;
  mode?: 'LIST' | 'SHOW' | 'NONE';
}

function HorizontalLine() {
  return <div className="w-full h-[0.1rem] bg-custom-medium-gray"></div>;
}

export default function FlexBox({
  mode = 'SHOW',
  isDivided,
  topChildren,
  bottomChildren,
  date,
  ...props
}: FlexBoxProps) {
  return (
    <div>
      {date && (
        <p className="text-custom-dark-gray ml-[1rem] mt-[1rem]">{`${new Date(
          date,
        ).getFullYear()}년 ${new Date(date).getMonth() + 1}월 ${new Date(
          date,
        ).getDate()}일`}</p>
      )}
      <section
        className={`w-full flex flex-col ${
          mode === 'NONE' ? 'bg-custom-white' : 'bg-custom-light-gray'
        } rounded-[2rem]
        ${
          mode === 'LIST'
            ? 'p-[1rem]'
            : mode === 'NONE'
            ? 'P-[0.5rem]'
            : 'p-[2rem]'
        } px-[1.5rem] gap-[1.4rem]`}
        {...props}
      >
        <>{topChildren}</>
        {isDivided && <HorizontalLine />}
        <>{bottomChildren}</>
      </section>
    </div>
  );
}
