/**
 * Copyright (c) Daniel Doubrovkine, 2010
 * dblock[at]dblock[dot]org
 * All Rights Reserved
 * Eclipse Public License (EPLv1)
 * http://oshi.codeplex.com/license
 */
package oshi.software.os.windows.nt;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinBase.MEMORYSTATUSEX;
import oshi.hardware.Memory;

/**
 * Memory obtained by GlobalMemoryStatusEx.
 * @author dblock[at]dblock[dot]org
 */
public class GlobalMemory implements Memory {
	MEMORYSTATUSEX _memory = new MEMORYSTATUSEX();

	public GlobalMemory() {
    	if (! Kernel32.INSTANCE.GlobalMemoryStatusEx(_memory)) {
    		throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
    	}
	}
	
	@Override
	public long getAvailable() {
		return _memory.ullAvailPhys.longValue();
	}

	@Override
	public long getTotal() {
		return _memory.ullTotalPhys.longValue();
	}
}
